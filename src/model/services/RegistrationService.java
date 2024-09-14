package model.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
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
import model.util.Formatter;

public abstract class RegistrationService {
	static Scanner input = new Scanner(System.in);
	static Random random = new Random();
	
	public static Agency registerAgency(){
		String agencyFilepath = "C:/simpleBank/files/agencys.csv";
		UI.clearScreen();
		UI.printANSCIILogo();
		System.out.println("\t\t\t\tAgency Data");
		
		String agencyCode = String.format("%04d", random.nextInt(1, 9999));
		String agencyAddress = "";
		while(true) { 
			UI.clearScreen();
			UI.printANSCIILogo();
			System.out.println("\t\t\t\tAgency Data");
			System.out.println("\t\t\t\tAgency code: "+UI.ANSI_GREEN+agencyCode+UI.ANSI_RESET);
			System.out.print("\t\t\t\tAgency address: ");
			agencyAddress = input.nextLine().toUpperCase();
			try {
				if(ValidationService.validateAgency(new File(agencyFilepath), new Agency(agencyCode, agencyAddress))) {
					break;
				}
			}catch(InvalidDataException e) {
				System.out.println(e.getMessage());
				try {
					Thread.sleep(2000);
				}catch (InterruptedException i){
					i.printStackTrace();
				}
			}catch(InvalidOperationException e) {
				System.out.println(e.getMessage());
				try {
					Thread.sleep(2000);
				}catch (InterruptedException i){
					i.printStackTrace();
				}
			}catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
				try {
					Thread.sleep(2000);
				}catch (InterruptedException i){
					i.printStackTrace();
				}
			}
		}
		Agency agency = new Agency(agencyCode, agencyAddress.toUpperCase());
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
				clientName = Formatter.formatName(input.nextLine().trim());
				break;
			}catch(InvalidNameException e) {
				System.out.println(e.getMessage());
			}
		}
		while(true) {
			System.out.print("\t\t\t\tClient CPF: ");
			try {
				clientCPF = Formatter.formatCPF(input.nextLine().trim());
				break;
			}catch(InvalidCPFExcpetion e) {
				System.out.println(e.getMessage());
			}
		}
		while(true) {
			System.out.print("\t\t\t\tClient Phone Number: ");
			try {
				clientPhoneNumber = Formatter.formatPhoneNumber(input.nextLine().trim());
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
		File accsFile = new File(accsFilePath);
		Client client = registerClient();
		String numberAcc = "";
		String password = "";
		if(!accsFile.exists()) {
			try{
				accsFile.createNewFile();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		UI.clearScreen();
		UI.printANSCIILogo();

		System.out.println("\n\t\t\t\t"+UI.ANSI_GREEN+"Account Data"+UI.ANSI_RESET);
		Set<Account> listAllAcc = new HashSet<>();
		try {
			listAllAcc = OtherService.loadAccountList(accsFile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		if(listAllAcc != null) {
			for(Account acc : listAllAcc) {
				if(acc.getAgencyCode().equals(agency.getAgencyCode()) && acc.getClient().getCpfClient().equals(client.getCpfClient())) {
					agency.addAccount(acc);
				}
			}
		}
		while(true) {
			numberAcc = String.format("%04d", random.nextInt(1, 9999));
			for(Account acc : agency.getAccountsList()) {
				if(numberAcc.equals(acc.getNumberAccount())) {
					numberAcc = String.format("%04d", random.nextInt(1, 9999));
				}
			}
			break;
		}
		System.out.println("\t\t\t\tNumber Account: " +UI.ANSI_GREEN+numberAcc+UI.ANSI_RESET);
		while(true) {
			try {
				System.out.print("\t\t\t\tAccount password: ");
				password = input.nextLine();
				ValidationService.validatePassword(password);
				break;
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		Account newAccount = new Account(agency.getAgencyCode(), numberAcc, password, client);
		if(agency.getAccountsList().contains(newAccount)) {
			throw new InvalidDataException("This Account is already registered!!");
		}
		agency.addAccount(newAccount);
		try (BufferedWriter br = new BufferedWriter(new FileWriter(accsFilePath, true))){
			br.write(newAccount.getAgencyCode() + "," + newAccount.getNumberAccount() + "," 
					+ newAccount.getPasswordAccount() + "," + newAccount.getBalance() + ","
					+ newAccount.getClient().getCpfClient() + "," + newAccount.getClient().getNameClient() + ","
					+ newAccount.getClient().getPhoneNumberClient());
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