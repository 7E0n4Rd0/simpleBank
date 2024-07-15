package model.services;

public class DataValidator {
	public static void validateAccount(Integer numberAcc, Integer passwordAcc) {
		
	}
	// Soon I'll remove this returning Strings and put the try-catch.
	public static void validateCPF(String cpf) {
		if(!cpf.matches("\\d{3}[.]?\\d{3}[.]?\\d{3}[-]?\\d{2}")) {
			System.out.println("Invalid CPF"); 
		}
	}
	
	public static void validateName(String name) {
		if(!name.matches("\\w{4,}")) {
			System.out.println("Invalid Name");
		}
	}
	
	public static void validatePhoneNumber(String phoneNumber) {
		if(!phoneNumber.matches("[(]?\\d{2}[)]?\\d{4,5}[-]?\\d{4}")){
			System.out.println("This number is invalid");
	 	}
	}
}
