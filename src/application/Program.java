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
import java.util.Random;
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
	
	public static void main(String[] args) throws InvalidOperationException{
		Locale.setDefault(Locale.ENGLISH);
		Scanner input = new Scanner(System.in);
		File simpleBankDir = new File("C:/simpleBank/files");
		if(!simpleBankDir.mkdirs() && !simpleBankDir.exists()) {
			throw new InvalidOperationException("Something went wrong, couldn't make directory '/files'");
		}
		String n = "";
		
		while(true){
			try {
				UI.clearScreen();
				UI.mainMenu();
				n = input.nextLine();
				if(OtherService.isNumber(n)) {
					Short number = Short.parseShort(n);  
					if(number > 4 || number <= 0) {
						throw new IndexOutOfBoundsException(UI.ANSI_YELLOW + "\t\t\t\tError: there is not an option for this number!" + UI.ANSI_RESET);
					}
					switch(number){
					case 1:
						if(!simpleBankDir.exists()) {
							throw new InvalidOperationException("Something went wrong, couldn't find directory '/files'");
						}
						RegistrationService.registerAgency();
						try {
							Thread.sleep(3000);
						}catch (InterruptedException i){
							i.printStackTrace();
						}
						break;
					case 2:
						if(!simpleBankDir.exists()) {
							throw new InvalidOperationException("Something went wrong, couldn't find directory '/files'");
						}
						Set<Agency> agencys = OtherService.loadAgencyList(new File("C:/simpleBank/files/agencys.csv"));
						try (BufferedReader br = new BufferedReader(new FileReader("C:/simpleBank/files/agencys.csv"))){
							Random random = new Random();
							int randomAgency = 0;
							if(agencys.size() > 1) {
								randomAgency = random.nextInt(1, agencys.size());
							}else if(agencys.size() == 1) {
								randomAgency = 1;
							}else {
								throw new InvalidOperationException("You can't create an account without an agency registered!!!");
							}
							int counter = 1;
							Agency agencyChosed = null;
							for(Agency agency : agencys) {
								if(counter == randomAgency) {
									agencyChosed = new Agency(agency.getAgencyCode(), agency.getAgencyAddress());
								}
							}
							RegistrationService.registerAccount(agencyChosed);
						}catch (InvalidDataException e){
							System.out.println(e.getMessage());
							try {
								Thread.sleep(1000);
							}catch (InterruptedException i){
								i.printStackTrace();
							}
						}catch (IOException e) {
							System.out.println(e.getMessage());
							try {
								Thread.sleep(1000);
							}catch (InterruptedException i){
								i.printStackTrace();
							}
						}
						break;
					case 3:
						UI.clearScreen();
						if(!simpleBankDir.exists()) {
							throw new InvalidOperationException("Something went wrong, couldn't find directory '/files'");
						}
						String fileAccspath = "C:/simpleBank/files/accounts.csv";
						File fileAccs = new File(fileAccspath);
						if(!fileAccs.exists()) {
							throw new FileNotFoundException("\t\t\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the accounts.csv file\n" + UI.ANSI_RESET);
						}
						Set<Account> listAcc = new HashSet<Account>();
						listAcc.addAll(OtherService.loadAccountList(fileAccs));
						UI.printANSCIILogo();
						String numberAcc = "", clientCPF = "";
						System.out.println("\t\t\t\t\t"+UI.ANSI_GREEN+"Inform the account data"+UI.ANSI_RESET);
						while(true) {
							System.out.print("\t\t\t\t\tNumber Account: ");
							try {
								numberAcc = input.nextLine();
								OtherService.isNumber(numberAcc);
								if(numberAcc.length() > 4 || numberAcc.length() < 4) {
									throw new IllegalArgumentException(UI.ANSI_YELLOW+"\t\t\t\t\tThe Number Account is 4 digits only!!"+UI.ANSI_RESET);
								}else {
									break;
								}
							}catch(NumberFormatException e) {
								System.out.println(e.getMessage());
								try {
									Thread.sleep(1000);
								}catch (InterruptedException i){
									i.printStackTrace();
								}
							}catch(IllegalArgumentException e) {
								System.out.println(e.getMessage());
								try {
									Thread.sleep(1000);
								}catch (InterruptedException i){
									i.printStackTrace();
								}
							}
						}
						while(true) {
							System.out.print("\t\t\t\t\tClient CPF: ");
							try {
								clientCPF = input.nextLine();
								ValidationService.validateCPF(clientCPF);
								clientCPF = FormatterService.formatCPF(clientCPF);
								break;
							}catch(InvalidCPFExcpetion e) {
								System.out.println(e.getMessage());
							}
						}
						try {
							final String innerNumberAcc = new String(numberAcc);
							final String innerClientCPF = new String(clientCPF);
							if(!OtherService.findAccount(listAcc, innerNumberAcc, innerClientCPF)) {
								throw new InvalidOperationException("Couldn't find the account!!");
							}
							while(true) {
								System.out.print("\t\t\t\t"+UI.ANSI_RED+"[Destructive Action]"+UI.ANSI_RESET+
										" Are you sure you want to delete the account "
										+ "[" +"Number Account: "+innerNumberAcc +"; CPF: "+ innerClientCPF+"] [Y/N]?\n");
								try {
									Thread.sleep(2000);
								}catch (InterruptedException i){
									i.printStackTrace();
								}
								System.out.print("\t\t\t\t\t"+UI.ANSI_RED+">>> "+UI.ANSI_RESET);
								String answer = input.nextLine().toUpperCase();
								if(answer.charAt(0) == 'Y') {
									listAcc.removeIf(x -> x.getNumberAccount().equals(innerNumberAcc) && x.getClient().getCpfClient().equals(innerClientCPF));
									try (BufferedWriter br = new BufferedWriter(new FileWriter(fileAccspath))){
										for(Account account : listAcc) {
											br.append(account.getAgencyCode() + "," + account.getNumberAccount() + "," 
													+ account.getPasswordAccount() + "," + account.getBalance() + ","
													+ account.getClient().getCpfClient() + "," + account.getClient().getNameClient() + ","
													+ account.getClient().getPhoneNumberClient() + "\n");
										}
									}catch(IOException e) {
										System.out.println("Error: " + UI.ANSI_RED + e.getMessage() + UI.ANSI_RESET);
									}
									System.out.println("\t\t\t\t" + UI.ANSI_GREEN + "Account deleted with sucessfully!!" + UI.ANSI_RESET);
									try {
										Thread.sleep(3000);
									}catch (InterruptedException i){
										i.printStackTrace();
									}
									break;
								}else if(answer.charAt(0) == 'N') {
									System.out.println(UI.ANSI_YELLOW+"\t\t\t\t>>> Returning to the main menu..."+UI.ANSI_RESET);
									try {
										Thread.sleep(3000);
									}catch (InterruptedException i){
										i.printStackTrace();
									}
									break;
								}else {
									System.out.print(UI.ANSI_RED+"\t\t\t\tError: there is not an option for this caracter!\n "+UI.ANSI_RESET);
									try {
										Thread.sleep(1000);
									}catch (InterruptedException i){
										i.printStackTrace();
									}
								}
							}
						}catch(InvalidOperationException e) {
							System.out.println(e.getMessage());
							try {
								Thread.sleep(2000);
							}catch (InterruptedException i){
								i.printStackTrace();
							}
						}
						break;
					case 4:
						UI.clearScreen();
						UI.printANSCIILogo();
						System.out.println("\t\t\t\tBye Bye, have a nice day!!!");
						Thread.sleep(3000);
						break;
					}	
				}else if(n.toLowerCase().equals("linkin park") || n.toLowerCase().equals("meteora") || n.toLowerCase().equals("chester")) {
					UI.clearScreen();
					try {
						UI.printLP();
					}catch (InterruptedException e) {
						System.out.println("\t\t\t\tThe user interrupted the LP moment, what a shame!! ⋋_⋌");
					}
					input.nextLine();
				}else {
					throw new InputMismatchException();
				}
				
			}catch(InputMismatchException e) {
				System.out.print("\t\t\t\tError: "+UI.ANSI_RED+"Caracters different from numbers are not allowed!."+UI.ANSI_RESET+"\n"
						+ "\t\t\t\tpress enter key to try again");
				try {
					Thread.sleep(1000);
				}catch (InterruptedException i){
					i.printStackTrace();
				}
				input.nextLine();
			}catch(IndexOutOfBoundsException e) {
				System.out.println(e.getMessage() + "\n\t\t\t\tpress enter key to try again");
				try {
					Thread.sleep(1000);
				}catch (InterruptedException i){
					i.printStackTrace();
				}
				input.nextLine();
			}catch(FileNotFoundException e) {
				UI.clearScreen();
				UI.printANSCIILogo();
				System.out.println(e.getMessage() + "\n\t\t\t\tpress enter key to try again");
				try {
					Thread.sleep(1000);
				}catch (InterruptedException i){
					i.printStackTrace();
				}
				input.nextLine();
			} catch (InvalidOperationException e) {
				UI.clearScreen();
				UI.printANSCIILogo();
				System.out.println(e.getMessage() + "\n\t\t\t\tpress enter key to try again");
				try {
					Thread.sleep(1000);
				}catch (InterruptedException i){
					i.printStackTrace();
				}
				input.nextLine();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}catch (InvalidDataException e) {
				System.out.println(e.getMessage());
			}
			UI.clearScreen();
			if(n.equals("4")) {
				break;
			}
		}
	}
}