package model.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import application.UI;
import model.entities.Account;
import model.entities.Agency;
import model.entities.Client;
import model.excpetion.InvalidCPFExcpetion;
import model.excpetion.InvalidNameException;
import model.excpetion.InvalidPhoneNumberException;
import model.services.ValidationService;

public abstract class Utils {
	/**
	 * Format a CPF string in the structure: 000.000.000-00.
	 * @param cpf - Client CPF;
	 * @return cpf with the new format;
	 * @throws InvalidCPFExcpetion If something happens in validateCPF();
	 */
	public static String formatCPF(String cpf) throws InvalidCPFExcpetion {
		ValidationService.validateCPF(cpf);
		StringBuilder sb = new StringBuilder();
		sb.append(cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."); 
		sb.append(cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
		cpf = sb.toString();
		return cpf;
	}
	/**
	 * Format the client full name with the first letter in each name in upper case.
	 * @param name client name;
	 * @return The client name with the new format;
	 * @throws InvalidNameException If something happens during the validateName() or the name length is less than zero.
	 */
	public static String formatName(String name) throws InvalidNameException { // Affectionately nicknamed as 'title()' internally, 
		if(name.length() <= 0) {
			throw new InvalidNameException("The name cannot be blank or null!!");
		}
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
	/**
	 * Format the phone number in the structure: (00) 00000-0000.
	 * @param phoneNumber Client phone number;
	 * @return the phone number formated;
	 * @throws InvalidPhoneNumberException If something happens during the validatePhoneNumber().
	 */
	public static String formatPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException{
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
	/**
	 * Check if the number in string is a number or not.
	 * @param number - Number in String
	 * @return true if is a number, false if is not.
	 */
	public static boolean isNumber(String number){
		try {
			Integer.parseInt(number);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	/**
	 * Search an account on the accounts set with the number account and CPF client informed.  
	 * @param accounts - set of accounts;
	 * @param numberAcc - number account;
	 * @param cpfClient - CPF of the client.
	 * @return true if found, false if not found
	 */
	public static boolean findAccount(Set<Account> accounts, String numberAcc, String cpfClient){
		for(Account acc : accounts) {
			if(acc.getNumberAccount().contains(numberAcc) && acc.getClient().getCpfClient().contains(cpfClient)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * It reads a file for accounts and return a set of accounts. 
	 * @param file - Account file with a list of accounts registered;
	 * @return Set<Account> accounts;
	 * @throws FileNotFoundException
	 */
	public static Set<Account> loadAccountList(File file) throws FileNotFoundException{
		String path = file.getAbsolutePath();
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			Set<Account> accounts = new HashSet<Account>();
			while(line != null) {
				String[] field = line.split(",");
				accounts.add(new Account(field[0], field[1], field[2], new Client(field[4], field[5], field[6])));
				line = br.readLine();
			}
			return accounts;
		}catch(IOException e) {
			if(e.getClass().getClasses().toString().equals("FileNotFoundException")) {
				throw new FileNotFoundException("\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the agencys.csv file" + UI.ANSI_RESET 
						+ "\n \t\t\tpress enter key to try again");
			}else {
				System.out.println(e.getMessage());
			}
			return null;
		}
	}
	/**
	 * 
	 * It reads a file for agencys and return a set of agencys. 
	 * @param file - Agency file with a list of agencys registered;
	 * @return Set<Account> agencys;
	 * @throws FileNotFoundException
	 */
	public static Set<Agency> loadAgencyList(File file) throws FileNotFoundException {
		String path = file.getAbsolutePath();
		if(!file.exists()) {
			try{
				file.createNewFile();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			Set<Agency> agencys = new HashSet<Agency>();
			while(line != null && (!line.isBlank() || !line.isEmpty())) {
				String[] field = line.split(",");
				agencys.add(new Agency(field[0], field[1]));
				line = br.readLine();
			}
			return agencys;
		}catch(IOException e) {
			if(e.getClass().getClasses().toString().equals("FileNotFoundException")) {
				throw new FileNotFoundException("\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the agencys.csv file" + UI.ANSI_RESET 
						+ "\n \t\t\tpress enter key to try again");
			}
			return null;
		}
	}
}