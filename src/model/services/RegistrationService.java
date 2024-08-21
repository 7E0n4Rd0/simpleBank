package model.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import application.UI;
import model.entities.Account;
import model.entities.Agency;
import model.entities.Client;
import model.excpetion.InvalidCPFExcpetion;
import model.excpetion.InvalidDataException;
import model.excpetion.InvalidNameException;
import model.excpetion.InvalidPhoneNumberException;

public abstract class RegistrationService {
	static Scanner input = new Scanner(System.in);
	static Random random = new Random();
	
	public static Agency registerAgency() {
		String agencyFilepath = "C:/Users/leome/Desktop/Programming/Java/ws-eclipse/simpleBank/Files/agencys.csv";
		UI.clearScreen();
		UI.printANSCIILogo();
		System.out.println("\t\t\tAgency Data");
		System.out.print("\t\t\tAgency address: ");
		
		String agencyAddress = input.nextLine();
		String agencyCode = String.format("%04d", random.nextInt(1, 9999));
		Agency agency = new Agency(agencyCode, agencyAddress);
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(agencyFilepath))){
			br.append(agency.getAgencyCode() +","+agency.getAgencyAddress());
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return agency;
	}
	
	protected static Client registerClient() {
		UI.clearScreen();
		UI.printANSCIILogo();
		System.out.println("\n\t\t\t"+UI.ANSI_GREEN+"Client Data"+UI.ANSI_RESET);
		String clientName = "", clientCPF = "", clientPhoneNumber = "";
		while(true) {
			try {
				System.out.print("\t\t\tClient First Name and Last Name: ");
				clientName = FormatterService.formatName(input.nextLine().trim());
				break;
			}catch(InvalidNameException e) {
				System.out.println(e.getMessage());
			}
		}
		while(true) {
			System.out.print("\t\t\tClient CPF: ");
			try {
				clientCPF = FormatterService.formatCPF(input.nextLine().trim());
				break;
			}catch(InvalidCPFExcpetion e) {
				System.out.println(e.getMessage());
			}
		}
		while(true) {
			System.out.print("\t\t\tClient Phone Number: ");
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
		String accountsFilePath = "C:/Users/leome/Desktop/Programming/Java/ws-eclipse/simpleBank/Files/accounts.csv";
		Client client = registerClient();
		String numberAcc = "";
		String password = "";
		UI.clearScreen();
		UI.printANSCIILogo();
		while(true) {
			try {
				System.out.println("\n\t\t\t"+UI.ANSI_GREEN+"Account Data"+UI.ANSI_RESET);
				numberAcc = String.format("%04d", random.nextInt(1, 9999));
				System.out.print("\t\t\tAccount password: ");
				password = input.nextLine();
				ValidationService.validatePassword(password);
				break;
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		Account account = new Account(agency.getAgencyCode(), numberAcc, password, client);
		if(agency.getAccountsList().contains(account)) {
			throw new InvalidDataException("This account is already registered!!");
		}
		agency.addAccount(account);
		try (BufferedWriter br = new BufferedWriter(new FileWriter(accountsFilePath, true))){
			br.append(account.getAgencyCode() + "," + account.getNumberAccount() + "," 
					+ account.getPasswordAccount() + "," + account.getBalance() + ","
					+ account.getClient().getCpfClient() + "," + account.getClient().getNameClient() + ","
					+ account.getClient().getPhoneNumberClient() + "\n");
		}catch(IOException e) {
			System.out.println("Error: " + UI.ANSI_RED + e.getMessage() + UI.ANSI_RESET);
		}
		UI.clearScreen();
		UI.printANSCIILogo();
		System.out.println("\t\t\t" + UI.ANSI_GREEN + "Account created with sucessfully!!" + UI.ANSI_RESET);
	}
}