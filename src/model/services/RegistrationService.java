package model.services;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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
		System.out.println("Agency Data");
		System.out.print("Agency address: ");
		String agencyAddress = input.nextLine();
		int agencyCode = random.nextInt(1, 9999);
		Agency agency = new Agency(agencyCode, agencyAddress);
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
	
	public static Account registerAccount(Agency agency) {
		Client client = registerClient();
		int numberAcc = 0, password = 0;
		while(true) {
			try {
				System.out.println("\nAccount Data");
				numberAcc = random.nextInt(1, 9999);
				System.out.print("Account password: ");
				password = input.nextInt();
				ValidatorService.validatePassword(password);
				break;
			}catch(InputMismatchException e) {
				System.out.println("Error: was expected to only type numbers");
			}catch(IllegalArgumentException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		Account account = new Account(numberAcc, password, client);
		agency.addAccount(account);
		return account;
	}
}