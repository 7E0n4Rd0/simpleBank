package model.services;

import model.entities.Agency;

public abstract class ValidatorService {
	protected static void validateAccount(Agency agency,Integer numberAcc, Integer passwordAcc) {
		validatePassword(passwordAcc);
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
	
	protected static void validatePassword(Integer passwordAcc) {
		String passwordString = "" + passwordAcc;
		if(passwordString.length() > 4 || passwordString.length() < 4) {
			System.out.println("The password must contain 4 digits only.");
		}
	}
	
	// Soon I'll remove this returning Strings and put the try-catch.
	protected static void validateCPF(String cpf) {
		if(!cpf.matches("\\d{3}[.]?\\d{3}[.]?\\d{3}[-]?\\d{2}")) {
			System.out.println("Invalid CPF"); 
		}
	}
	
	protected static void validateName(String name) {
		if(!name.matches("\\w{3,}[\\s]?\\w*")) {
			System.out.println("Invalid Name");
		}
	}
	
	protected static void validatePhoneNumber(String phoneNumber) {
		if(!phoneNumber.matches("^[(]?[1-9]{2}[)]?(?:[2-8]|9[\\d])\\d{3}[-]?\\d{4}$")){
			System.out.println("This number is invalid");
	 	}
	}
	
	
}
