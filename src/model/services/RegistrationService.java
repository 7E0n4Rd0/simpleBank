package model.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import application.UI;
import model.entities.Account;
import model.entities.Agency;
import model.entities.Client;
import model.excpetion.InvalidCPFExcpetion;
import model.excpetion.InvalidDataException;
import model.excpetion.InvalidNameException;
import model.excpetion.InvalidOperationException;
import model.excpetion.InvalidPhoneNumberException;

public abstract class RegistrationService {
	static Scanner input = new Scanner(System.in);
	static Random random = new Random();
	
	public static Agency registerAgency() throws InvalidDataException {
		String agencyFilepath = "C:/simpleBank/files/agencys.csv";
		UI.clearScreen();
		UI.printANSCIILogo();
		System.out.println("\t\t\t\tAgency Data");
		
		String agencyCode = String.format("%04d", random.nextInt(1, 9999));
		System.out.println("\t\t\t\tAgency code: "+UI.ANSI_GREEN+agencyCode+UI.ANSI_RESET);
		System.out.print("\t\t\t\tAgency address: ");
		String agencyAddress = input.nextLine();
		
		if(agencyAddress.startsWith("[0-9]")) {
			throw new IllegalArgumentException("You can't start a adress with numbers!!");
		}else if(!agencyAddress.matches("^(Rua|Avenida|Travessa|Praça|Alameda)\\s[\\w\\s]+,\\s?\\d+(\\s?\\-\\s?[\\w\\s]+)?\\s\\-"
				+ "\\s[\\w\\s]+,\\s[\\w\\s]+,\\s[A-Z]{2},\\s\\d{5}-\\d{3}$\r\n")) { //Starts with G and ends with T 
			throw new InvalidDataException("Invalid Adress");
		}
		
		Agency agency = new Agency(agencyCode, agencyAddress);
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(agencyFilepath, true))){
			br.write(agency.getAgencyCode() +","+agency.getAgencyAddress());
			br.newLine();
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		System.out.println("\t\t\t\t" + UI.ANSI_GREEN + "Agency created with sucessfully!!" + UI.ANSI_RESET);
		return agency;
	}
	
	protected static Client registerClient() {
		UI.clearScreen();
		UI.printANSCIILogo();
		System.out.println("\n\t\t\t\t"+UI.ANSI_GREEN+"Client Data"+UI.ANSI_RESET);
		String clientName = "", clientCPF = "", clientPhoneNumber = "";
		while(true) {
			try {
				System.out.print("\t\t\t\tClient First Name and Last Name: ");
				clientName = FormatterService.formatName(input.nextLine().trim());
				break;
			}catch(InvalidNameException e) {
				System.out.println(e.getMessage());
			}
		}
		while(true) {
			System.out.print("\t\t\t\tClient CPF: ");
			try {
				clientCPF = FormatterService.formatCPF(input.nextLine().trim());
				break;
			}catch(InvalidCPFExcpetion e) {
				System.out.println(e.getMessage());
			}
		}
		while(true) {
			System.out.print("\t\t\t\tClient Phone Number: ");
			try {
				clientPhoneNumber = FormatterService.formatPhoneNumber(input.nextLine().trim());
				break;
			}catch(InvalidPhoneNumberException e) {
				System.out.println(e.getMessage());
			}
		}
		Client client = new Client(clientCPF,clientName,clientPhoneNumber);
		return client;
	
	}
	
	public static void registerAccount(Agency agency) throws InvalidDataException {
		String accsFilePath = "C:/simpleBank/files/accounts.csv";
		Client client = registerClient();
		String numberAcc = "";
		String password = "";
		UI.clearScreen();
		UI.printANSCIILogo();
		while(true) {
			try {
				System.out.println("\n\t\t\t\t"+UI.ANSI_GREEN+"Account Data"+UI.ANSI_RESET);
				numberAcc = String.format("%04d", random.nextInt(1, 9999));
				System.out.println("\t\t\t\tNumber Account: " +UI.ANSI_GREEN+numberAcc+UI.ANSI_RESET);
				System.out.print("\t\t\t\tAccount password: ");
				password = input.nextLine();
				ValidationService.validatePassword(password);
				break;
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		Account account = new Account(agency.getAgencyCode(), numberAcc, password, client);
		Set<Account> listAllAcc = new HashSet<>();
		try{
			listAllAcc = OtherService.loadAccountList(new File(accsFilePath));
		}catch(InvalidOperationException e) {
			System.out.println(e.getMessage());
		}
		if(listAllAcc != null) {
			for(Account acc : listAllAcc) {
				if(acc.getAgencyCode().equals(agency.getAgencyCode()) && acc.getClient().getCpfClient().equals(client.getCpfClient())) {
					agency.addAccount(acc);
				}
			}
		}
		if(agency.getAccountsList().contains(account)) {
			throw new InvalidDataException("This account is already registered!!");
		}
		agency.addAccount(account);
		try (BufferedWriter br = new BufferedWriter(new FileWriter(accsFilePath, true))){
			br.write(account.getAgencyCode() + "," + account.getNumberAccount() + "," 
					+ account.getPasswordAccount() + "," + account.getBalance() + ","
					+ account.getClient().getCpfClient() + "," + account.getClient().getNameClient() + ","
					+ account.getClient().getPhoneNumberClient());
			br.newLine();
		}catch(IOException e) {
			System.out.println("Error: " + UI.ANSI_RED + e.getMessage() + UI.ANSI_RESET);
		}
		UI.clearScreen();
		UI.printANSCIILogo();
		System.out.println("\t\t\t\t" + UI.ANSI_GREEN + "Account created with sucessfully!!" + UI.ANSI_RESET);
		try {
			Thread.sleep(2000);
		}catch (InterruptedException i){
			i.printStackTrace();
		}
	}
}