package aplication;


import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

import model.entities.Account;
import model.entities.Agency;
import model.entities.Client;
import model.services.DataFormatter;
import model.services.DataValidator;

public class Program {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		/*
		Agency agency = new Agency(102, "Linkin Park", "Street 342");
		Client client = new Client("109.098.787-12", "Arthur", 99098763);
		Account acc = new Account(1004, 1234, 0.0, client);
		
		System.out.println(agency +"\n"+ acc);
		*/
		/*System.out.print("CPF: ");
		String cpf = input.nextLine(); //CPF validator is almost completed
		System.out.println(cpf);
		DataValidator.validateCPF(cpf);*/
		
		/*System.out.print("Phone Number: ");
		String phoneNumber = input.nextLine();
		phoneNumber = DataFormatter.formatPhoneNumber(phoneNumber);
		System.out.println(phoneNumber);
		DataValidator.validatePhoneNumber(phoneNumber);*/
		
		
		System.out.print("Name: ");
		String name = input.nextLine();
		name = DataFormatter.formatClientName(name);
		System.out.println(name);
	}

}
