package model.services;

public class DataValidator {
	public static void validateAccount(Integer numberAcc, Integer passwordAcc) {
		
	}
	
	public static void validateCPF(String cpf) {
		if(cpf.matches("\\d{3}[.]\\d{3}[.]?\\d{3}[-]\\d{2}")) {
			System.out.println("Valid CPF"); //this is temporary.
		}else {
			System.out.println("Invalid CPF");
		}
	}
	
	public static void validateName(String name) {
		
	}
	
	public static void validatePhoneNumber(String phoneNumber) {
		
	}
}
