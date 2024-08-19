package model.services;

import application.UI;
import model.entities.Agency;
import model.excpetion.InvalidCPFExcpetion;
import model.excpetion.InvalidDataException;
import model.excpetion.InvalidNameException;
import model.excpetion.InvalidPhoneNumberException;

public abstract class ValidatorService {
	public static void validateAccount(Agency agency,Integer numberAcc, String passwordAcc) throws InvalidDataException {
		validatePassword(passwordAcc);
		for(int i = 0; i < agency.getAccountsList().size(); i++) {
			if(agency.getAccountsList().get(i).getNumberAccount().equals(numberAcc) &&
					!agency.getAccountsList().get(i).getPasswordAccount().equals(passwordAcc)){
				throw new InvalidDataException("The password is wrong.");
			}if(!agency.getAccountsList().get(i).getNumberAccount().equals(numberAcc) &&
					!agency.getAccountsList().get(i).getPasswordAccount().equals(passwordAcc) &&
					i == (agency.getAccountsList().size() - 1)){
				throw new InvalidDataException("The account is invalid");
			}
		}
	}
	
	protected static String validatePassword(String passwordString){
		if((passwordString.length() > 4 || passwordString.length() < 4) && !OtherService.isNumber(passwordString)) {
			throw new IllegalArgumentException(UI.ANSI_RED + "The password must contain 4 digits only and caracters different from numbers are not allowed!" + UI.ANSI_RESET);
		}if(passwordString.length() > 4 || passwordString.length() < 4) {
			throw new IllegalArgumentException(UI.ANSI_RED + "The password must contain 4 digits only." + UI.ANSI_RESET);
		}if(!OtherService.isNumber(passwordString)) {
			throw new NumberFormatException(UI.ANSI_RED + "Caracters different from numbers are not allowed!" + UI.ANSI_RESET);
		}
		return passwordString;
	}
	
	// Soon I'll remove this returning Strings and put the try-catch.
	protected static void validateCPF(String cpf) throws InvalidCPFExcpetion {
		if(!cpf.matches("^\\d{3}[.]?\\d{3}[.]?\\d{3}[-]?\\d{2}$")) { //This I tried hard to do and that 'validatePhoneNumber'
			throw new InvalidCPFExcpetion("Invalid CPF"); 
		}
	}
	
	protected static void validateName(String name) throws InvalidNameException {
		if(!name.matches("^(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?" //I just copied this from stackoverflow. I couldn't do all
				+ "(?:[\\p{Ll}&&[\\p{IsLatin}]]))+(?:\\-(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&" //this.
				+ "[\\p{IsLatin}]]))+)*(?: (?:(?:e|y|de(?:(?: la| las| lo| los))?|do|dos|da|das|del|van|von|bin|le) )?" 
				+ "(?:(?:(?:d'|D'|O'|Mc|Mac|al\\-))?(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&" 
				+ "[\\p{IsLatin}]]))+|(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+(?:\\-"
				+ "(?:[\\p{Lu}&&[\\p{IsLatin}]])(?:(?:')?(?:[\\p{Ll}&&[\\p{IsLatin}]]))+)*))+(?: (?:Jr\\.|II|III|IV))?$")) {
			throw new InvalidNameException("Invalid Name. Make sure you enter the first and last name correctly. ");
		}
	}
	
	protected static void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberException{
		if(!phoneNumber.matches("^[(]?[1-9]{2}[)]?(?:[2-8]|9[\\d])\\d{3}[-]?\\d{4}$")){
			throw new InvalidPhoneNumberException("This phone number is invalid");
	 	}
	}
}