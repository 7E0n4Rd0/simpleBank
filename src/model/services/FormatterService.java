package model.services;

public abstract class FormatterService {
	public static String formatCPF(String cpf) {
		cpf = cpf.strip();
		ValidatorService.validateCPF(cpf);
		StringBuilder sb = new StringBuilder();
		sb.append(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."); // 000.000.
		sb.append(cpf.substring(6, 9) + "-" + cpf.substring(9, 11)); // 000-00;
		cpf = sb.toString();
		return cpf;
	}
	
	public static String formatClientName(String name) {
		name = name.toUpperCase().strip();
		ValidatorService.validateName(name);
		return name;
	}
	
	public static String formatPhoneNumber(String phoneNumber){
 		phoneNumber = phoneNumber.strip();
 		ValidatorService.validatePhoneNumber(phoneNumber);
		StringBuilder sb = new StringBuilder();
	    sb.append("(" + phoneNumber.substring(0, 2) + ")" + phoneNumber.substring(2, 6) 
	    + "-" + phoneNumber.substring(6, 10));
	    phoneNumber = sb.toString();
	    return phoneNumber;
	}
	
}
