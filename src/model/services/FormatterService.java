package model.services;

import model.excpetion.InvalidCPFExcpetion;
import model.excpetion.InvalidNameException;
import model.excpetion.InvalidPhoneNumberException;

public abstract class FormatterService {
	public static String formatCPF(String cpf) throws InvalidCPFExcpetion {
		ValidationService.validateCPF(cpf);
		StringBuilder sb = new StringBuilder();
		sb.append(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."); 
		sb.append(cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
		cpf = sb.toString();
		return cpf;
	}
	
	protected static String formatName(String name) throws InvalidNameException { // Affectionately nicknamed as 'title()' internally, 
		String[] nameTrimed = name.split("\\s"); //because it is a method that is available in the snake 
		String formatedName = "";						//and not available in this cup of coffee. I'd rather coffee than
		for(int i = 0; i < nameTrimed.length; i++) {    //not braces programming language.  
			formatedName += nameTrimed[i].replace(nameTrimed[i].charAt(0), 
					nameTrimed[i].toUpperCase().charAt(0)) + "\s";
		}
		name = name.replace(name, formatedName.trim());
		ValidationService.validateName(name);
		return name;
	}
	
	protected static String formatPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException{
 		ValidationService.validatePhoneNumber(phoneNumber);
		StringBuilder sb = new StringBuilder();
		if(phoneNumber.length() == 10) {
			sb.append("(" + phoneNumber.substring(0, 2) + ")" + phoneNumber.substring(2, 6) 
		    + "-" + phoneNumber.substring(6, 10));
		    phoneNumber = sb.toString();
		}else if(phoneNumber.length() == 11){
			sb.append("(" + phoneNumber.substring(0, 2) + ")" + phoneNumber.substring(2, 7) 
		    + "-" + phoneNumber.substring(7, 11));
		    phoneNumber = sb.toString();
		}
	    
	    return phoneNumber;
	}
	
}