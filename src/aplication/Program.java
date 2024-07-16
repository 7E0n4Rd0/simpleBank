package aplication;


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
		
		
		Agency agency = new Agency(102, "Linkin Park", "Street 342");
		Client client = new Client("10909878712", "Arthur", "3199098763");
		Account acc = new Account(1004, 1234, 0.0, client);
		agency.addAccount(acc);
		agency.addAccount(new Account(1023, 3214, 0.0, new Client("32165498745", "Steve Rogers", "1123456987")));
		System.out.println(agency +"\n"+ acc);
		
		/*System.out.print("CPF: ");
		String cpf = input.nextLine(); //CPF validator is almost completed
		System.out.println(cpf);
		ValidatorService.validateCPF(cpf);
		
		System.out.print("Phone Number: ");
		String phoneNumber = input.nextLine();
		phoneNumber = FormatterService.formatPhoneNumber(phoneNumber);
		System.out.println(phoneNumber);
		ValidatorService.validatePhoneNumber(phoneNumber);
		
		
		System.out.print("Name: ");
		String name = input.nextLine();
		name = FormatterService.formatClientName(name);
		System.out.println(name);*/

		ValidatorService.validateAccount(agency, 1014, 12345);
	}

}
