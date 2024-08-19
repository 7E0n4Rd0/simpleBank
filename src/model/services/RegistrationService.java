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
		int agencyCode = random.nextInt(1, 9999);
		Agency agency = new Agency(agencyCode, agencyAddress);
		
		try (BufferedWriter br = new BufferedWriter(new FileWriter(agencyFilepath))){
			br.append(agency.getAgencyCode() +","+agency.getAgencyAddress());
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return agency;
	}
	
	protected static Client registerClient() {
		System.out.println("\nClient Data");
		String clientName = "", clientCPF = "", clientPhoneNumber = "";
		while(true) {
			try {
				System.out.print("Client First Name and Last Name: ");
				clientName = FormatterService.formatName(input.nextLine().trim());
				break;
			}catch(InvalidNameException e) {
				System.out.println(e.getMessage());
			}
		}
		while(true) {
			System.out.print("Client CPF: ");
			try {
				clientCPF = FormatterService.formatCPF(input.nextLine().trim());
				break;
			}catch(InvalidCPFExcpetion e) {
				System.out.println(e.getMessage());
			}
		}
		while(true) {
			System.out.print("Client Phone Number: ");
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
	
	public static void registerAccount(Agency agency) {
		String accountsFilePath = "C:/Users/leome/Desktop/Programming/Java/ws-eclipse/simpleBank/Files/accounts.csv";
		Client client = registerClient();
		int numberAcc = 0;
		String password = "";
		while(true) {
			try {
				System.out.println("\nAccount Data");
				numberAcc = random.nextInt(1, 9999);
				System.out.print("Account password: ");
				password = input.nextLine();
				ValidationService.validatePassword(password);
				break;
			}catch(IllegalArgumentException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		Account account = new Account(agency.getAgencyCode(),numberAcc, password, client);
		agency.addAccount(account);
		try (BufferedWriter br = new BufferedWriter(new FileWriter(accountsFilePath, true))){
			br.append(agency.getAccountsList().toString() + ",\n");
		}catch(IOException e) {
			System.out.println("Error: " + UI.ANSI_RED + e.getMessage() + UI.ANSI_RESET);
		}
		System.out.println(UI.ANSI_GREEN + "Account created with sucessfully!!" + UI.ANSI_RESET);
	}
}