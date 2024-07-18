package model.services;

import java.util.Scanner;

import model.entities.Agency;
import model.excpetion.InvalidDataException;

public abstract class OtherService {
	public static void login(Agency agency) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the account number: ");
		int numberAcc = input.nextInt();
		System.out.print("Enter the password: ");
		int passwordAcc = input.nextInt();
		try {
			ValidatorService.validateAccount(agency, numberAcc, passwordAcc);
		}catch(InvalidDataException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}