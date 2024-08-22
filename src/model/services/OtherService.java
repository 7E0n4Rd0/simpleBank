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
import model.entities.Agency;
import model.entities.Client;
import model.excpetion.InvalidOperationException;

public abstract class OtherService {
	public static boolean isNumber(String string){
		try {
			Integer.parseInt(string);
		}catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static Set<Account> loadAccountList(File file) throws InvalidOperationException {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String path = file.getAbsolutePath();
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			if(!file.exists()) {
				throw new FileNotFoundException();
			}
			String line = br.readLine();
			if(line == null) {
				throw new InvalidOperationException("Fatal Error: The file accounts.csv is null!!");
			}
			Set<Account> accounts = new HashSet<Account>();
			while(line != null) {
				String[] field = line.split(",");
				accounts.add(new Account(field[0], field[1], field[2], new Client(field[4], field[5], field[6])));
				line = br.readLine();
			}
			return accounts;
		}catch(IOException e) {
			if(e.getClass().getClasses().toString().equals("FileNotFoundException")) {
				System.out.println("\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the accounts.csv file" + UI.ANSI_RESET 
						+ "\n \t\t\tpress enter key to try again");
				return null;
			}
			System.out.println(e.getMessage());
			return null;
		}
	}
	public static Set<Agency> loadAgencyList(File file) throws InvalidOperationException {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		String path = file.getAbsolutePath();
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			if(!file.exists()) {
				throw new FileNotFoundException();
			}
			String line = br.readLine();
			if(line == null) {
				throw new NullPointerException("Fatal Error: The file agencys.csv is null!!");
			}
			Set<Agency> agencys = new HashSet<Agency>();
			while(line != null) {
				String[] field = line.split(",");
				agencys.add(new Agency(field[0], field[1]));
				line = br.readLine();
			}
			return agencys;
		}catch(IOException e) {
			if(e.getClass().getClasses().toString().equals("FileNotFoundException")) {
				System.out.println("\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the agencys.csv file" + UI.ANSI_RESET 
						+ "\n \t\t\tpress enter key to try again");
				return null;
			}
			System.out.println(e.getMessage());
			return null;
		}
	}
}