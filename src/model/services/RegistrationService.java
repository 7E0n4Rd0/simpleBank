package model.services;

import java.util.Random;
import java.util.Scanner;

import model.entities.Account;
import model.entities.Agency;
import model.entities.Client;

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
	
	private static Client registerClient() {
		System.out.println("\nClient Data");
		System.out.print("Client name: ");
		String clientName = FormatterService.formatName(input.nextLine());
		System.out.print("Client CPF: ");
		String clientCPF = FormatterService.formatCPF(input.nextLine());
		System.out.print("Client Phone Number: ");
		String clientPhoneNumber = FormatterService.formatPhoneNumber(input.nextLine());
		Client client = new Client(clientCPF,clientName,clientPhoneNumber);
		return client;
	}
	
	public static Account registerAccount(Agency agency) {
		Client client = registerClient();
		System.out.println("\nAccount Data");
		int numberAcc = random.nextInt(1, 9999);
		System.out.print("Account password: ");
		int password = input.nextInt();
		ValidatorService.validatePassword(password);
		Account account = new Account(numberAcc, password, client);
		agency.addAccount(account);
		return account;
	}
}
