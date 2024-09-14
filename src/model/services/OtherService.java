package model.services;

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
import model.excpetion.InvalidDataException;

public abstract class OtherService {
	public static boolean isNumber(String string){
		try {
			Integer.parseInt(string);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean findAccount(Set<Account> accounts, String numberAcc, String cpfClient){
		for(Account acc : accounts) {
			if(acc.getNumberAccount().contains(numberAcc) && acc.getClient().getCpfClient().contains(cpfClient)) {
				return true;
			}
		}
		return false;
	}
	
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
	public static Set<Agency> loadAgencyList(File file) throws InvalidDataException, FileNotFoundException {
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