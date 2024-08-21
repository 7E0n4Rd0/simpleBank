package model.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import application.UI;
import model.entities.Account;
import model.entities.Client;

public abstract class OtherService {
	public static boolean isNumber(String string){
		try {
			Integer.parseInt(string);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static void loadAccountList(File file) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String path = file.getAbsolutePath();
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			if(!file.exists()) {
				throw new FileNotFoundException();
			}
			String line = br.readLine();
			String[] field = line.split(",");
			
			/*Set<Account> accounts = new HashSet<Account>();
			while(line != null) {
				accounts.add(new Account(field[0], field[1], field[2], new Client(field[4], field[5], field[6])));
			}*/
		}catch(IOException e) {
			if(e.getClass().getClasses().toString().equals("FileNotFoundException")) {
				System.out.println("\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the accounts.csv file" + UI.ANSI_RESET 
						+ "\n \t\t\tpress enter key to try again");
			}
			System.out.println(e.getMessage());
		}
		
	}
	
}