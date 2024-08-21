package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import model.entities.Account;
import model.entities.Agency;
import model.excpetion.InvalidCPFExcpetion;
import model.excpetion.InvalidDataException;
import model.excpetion.InvalidOperationException;
import model.services.FormatterService;
import model.services.OtherService;
import model.services.RegistrationService;
import model.services.ValidationService;

public class Program {
	
	public static void main(String[] args){
		Locale.setDefault(Locale.ENGLISH);
		Scanner input = new Scanner(System.in);
		String n = "";
		
		while(true){
			try {
				UI.clearScreen();
				UI.mainMenu();
				n = input.nextLine();
				if(OtherService.isNumber(n)) {
					Short number = Short.parseShort(n);  
					if(number > 4 || number <= 0) {
						throw new IndexOutOfBoundsException("\t\t\tError: there is not an option for this number!\n \t\t\tpress enter key to try again");
					}
					switch(number){
					case 1:
						Agency agency = RegistrationService.registerAgency();
						break;
					case 2:
						String path = "C:/Users/leome/Desktop/Programming/Java/ws-eclipse/simpleBank/Files/agencys.csv";
						File file = new File(path);
						boolean fileExists = false;
						try (BufferedReader br = new BufferedReader(new FileReader(path))){
							if(!file.exists()) {
								fileExists = false;
								break;
							}
							fileExists = true;
							String line = br.readLine();
							String[] fields = line.split(",");
							RegistrationService.registerAccount(new Agency(fields[0], fields[1]));
							
						}catch (IOException e) {
							System.out.println("Error: " + e.getMessage());
						} catch (InvalidDataException e) {
							System.out.println(e.getMessage());
						}
						if(!fileExists) {
							throw new FileNotFoundException();
						}
						break;
					case 3:
						UI.clearScreen();
						UI.printANSCIILogo();
						String numberAcc = "", clientCPF = "";
						System.out.println("\t\t\tInform the account data");
						while(true) {
							System.out.print("\t\t\tNumber Account: ");
							try {
								numberAcc = input.nextLine();
								OtherService.isNumber(numberAcc);
							}catch(NumberFormatException e) {
								System.out.println(e.getMessage());
							}
							break;
						}
						while(true) {
							System.out.print("\t\t\tClient CPF: ");
							try {
								clientCPF = input.nextLine();
								ValidationService.validateCPF(clientCPF);
							}catch(InvalidCPFExcpetion e) {
								System.out.println(e.getMessage());
							}
							break;
						}
						String fileAccspath;
						try {
							fileAccspath = "C:/Users/leome/Desktop/Programming/Java/ws-eclipse/simpleBank/Files/accounts.csv";
							File fileAccs = new File(fileAccspath);
							Set<Account> listAcc = new HashSet<Account>();
							final String innerNumberAcc = new String(numberAcc);
							final String innerClientCPF = new String(FormatterService.formatCPF(clientCPF));
							listAcc.addAll(OtherService.loadAccountList(fileAccs));
							if(!ValidationService.validateAccount(listAcc, innerNumberAcc, innerClientCPF)) {
								throw new InvalidOperationException("Couldn't find the account!!");
							}
							System.out.print("\t\t\t"+UI.ANSI_RED+"[Destructive Action]"+UI.ANSI_RESET+
									"Are you sure you want to delete the account [" +"Number Account: "+innerNumberAcc +" CPF: "+ innerClientCPF+"]?[Y/N]\n\t\t\t>>>");
							String answer = input.nextLine().toUpperCase();
							while(answer.charAt(0) != 'Y' || answer.charAt(0) != 'N') {
								System.out.print("\t\t\tError: there is not an option for this caracter!\n "
										+ "\t\t\tpress enter key to try again");
								answer = input.nextLine().toUpperCase();
								if(answer.charAt(0) == 'Y') {
									listAcc.removeIf(x -> x.getNumberAccount().equals(innerNumberAcc) && x.getClient().getCpfClient().equals(innerClientCPF));
									try (BufferedWriter br = new BufferedWriter(new FileWriter(fileAccspath, true))){
										for(Account account : listAcc) {
											br.append(account.getAgencyCode() + "," + account.getNumberAccount() + "," 
													+ account.getPasswordAccount() + "," + account.getBalance() + ","
													+ account.getClient().getCpfClient() + "," + account.getClient().getNameClient() + ","
													+ account.getClient().getPhoneNumberClient() + "\n");
										}
									}catch(IOException e) {
										System.out.println("Error: " + UI.ANSI_RED + e.getMessage() + UI.ANSI_RESET);
									}
								}else if(answer.charAt(0) != 'N') {
									System.out.println(">>> Returning to the main menu...");
									break;
								}
							}
						}catch(InvalidOperationException | InvalidCPFExcpetion e) {
							System.out.println(e.getMessage());
						}
						
						break;
					case 4:
						UI.clearScreen();
						UI.printANSCIILogo();
						System.out.println("\t\t\tBye Bye, have a nice day!!!");
						break;
					}	
				}else {
					throw new InputMismatchException();
				}
				
			}catch(InputMismatchException e) {
				System.out.print("\t\t\tError: Caracters different from numbers are not allowed!.\n"
						+ "\t\t\tpress enter key to try again");
				input.nextLine();
			}catch(IndexOutOfBoundsException e) {
				input.nextLine();
			}catch(FileNotFoundException e) {
				UI.clearScreen();
				UI.printANSCIILogo();
				System.out.println("\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the agencys.csv file" + UI.ANSI_RESET 
						+ "\n \t\t\tpress enter key to try again");
				input.nextLine();
			}
			UI.clearScreen();
			if(n.equals("4")) {
				break;
			}
		}
	}
}