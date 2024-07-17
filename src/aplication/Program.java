package aplication;


import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

import model.entities.Account;
import model.entities.Agency;
import model.entities.Client;
import model.services.FormatterService;
import model.services.ValidatorService;

public class Program {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Random random = new Random();
		System.out.println("Agency Data");
		System.out.print("Bank name: ");
		String bankName = FormatterService.formatName(input.nextLine());
		System.out.print("Agency address: ");
		String agencyAddress = input.nextLine();
		int agencyCode = random.nextInt(000, 999);
		
		Agency agency = new Agency(agencyCode, bankName, agencyAddress);
		System.out.println(agency);
		
		System.out.println("Client Data");
		System.out.print("Client name: ");
		String clientName = FormatterService.formatName(input.nextLine());
		System.out.print("Client CPF: ");
		String clientCPF = FormatterService.formatCPF(input.nextLine());
		System.out.print("Client Phone Number: ");
		String clientPhoneNumber = FormatterService.formatPhoneNumber(input.nextLine());
		
		System.out.println(new Client(clientCPF, clientName, clientPhoneNumber));
	}
	
}
