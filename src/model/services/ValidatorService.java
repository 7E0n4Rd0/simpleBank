package model.services;

import model.entities.Agency;

public abstract class ValidatorService {
	public static void validateAccount(Agency agency,Integer numberAcc, Integer passwordAcc) {
		for(int i = 0; i < agency.getAccountsList().size(); i++) {
			if(agency.getAccountsList().get(i).getNumberAccount().equals(numberAcc) &&
					agency.getAccountsList().get(i).getPasswordAccount().equals(passwordAcc)) {
				System.out.println("This account is valid");
			}if(agency.getAccountsList().get(i).getNumberAccount().equals(numberAcc) &&
					!agency.getAccountsList().get(i).getPasswordAccount().equals(passwordAcc)){
				System.out.println("The password is wrong.");
			}if(!agency.getAccountsList().get(i).getNumberAccount().equals(numberAcc) &&
					!agency.getAccountsList().get(i).getPasswordAccount().equals(passwordAcc) &&
					i == (agency.getAccountsList().size() - 1)){
				System.out.println("The account is invalid");
			}
		}
		
	}
	// Soon I'll remove this returning Strings and put the try-catch.
	public static void validateCPF(String cpf) {
		if(!cpf.matches("\\d{3}[.]?\\d{3}[.]?\\d{3}[-]?\\d{2}")) {
			System.out.println("Invalid CPF"); 
		}
	}
	
	public static void validateName(String name) {
		if(!name.matches("\\w{3,}")) {
			System.out.println("Invalid Name");
		}
	}
	
	public static void validatePhoneNumber(String phoneNumber) {
		if(!phoneNumber.matches("[(]?\\d{2}[)]?\\d{4,5}[-]?\\d{4}")){
			System.out.println("This number is invalid");
	 	}
	}
}
