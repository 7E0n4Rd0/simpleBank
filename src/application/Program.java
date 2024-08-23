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
						throw new IndexOutOfBoundsException("\t\t\tError: there is not an option for this number!\n\t\t\tpress enter key to try again");
					}
					switch(number){
					case 1:
						if(!simpleBankDir.exists()) {
							throw new InvalidOperationException("Something went wrong, couldn't find directory '/files'");
						}
						RegistrationService.registerAgency();
						break;
					case 2:
						Random random = new Random();
						if(!simpleBankDir.exists()) {
							throw new InvalidOperationException("Something went wrong, couldn't find directory '/files'");
						}
						String agencysPath = "C:/simpleBank/files/agencys.csv";
						File file = new File(agencysPath);
						Set<Agency> agencys = OtherService.loadAgencyList(file);
						try (BufferedReader br = new BufferedReader(new FileReader(agencysPath))){
							int randomAgency = random.nextInt(1, agencys.size());
							int counter = 1;
							Agency agencyChosed = null;
							for(Agency agency : agencys) {
								if(counter == randomAgency) {
									agencyChosed = new Agency(agency.getAgencyCode(), agency.getAgencyAddress());
								}
							}
							RegistrationService.registerAccount(agencyChosed);
						}catch (InvalidDataException | IOException e){
							System.out.println(e.getMessage());
						}
						if(!file.exists()) {
							throw new FileNotFoundException("\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the agencys.csv file\n" + UI.ANSI_RESET);
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
							throw new FileNotFoundException("\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the accounts.csv file\n" + UI.ANSI_RESET);
						}
						Set<Account> listAcc = new HashSet<Account>();
						try {
							listAcc.addAll(OtherService.loadAccountList(fileAccs));
						}catch(InvalidOperationException e) {
							System.out.println(e.getMessage());
							break;
						}
						UI.printANSCIILogo();
						String numberAcc = "", clientCPF = "";
						System.out.println("\t\t\t"+UI.ANSI_GREEN+"Inform the account data"+UI.ANSI_RESET);
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
						try {
							final String innerNumberAcc = new String(numberAcc);
							final String innerClientCPF = new String(FormatterService.formatCPF(clientCPF));
							if(!ValidationService.validateAccount(listAcc, innerNumberAcc, innerClientCPF)) {
								throw new InvalidOperationException("Couldn't find the account!!");
							}
							while(true) {
								System.out.print("\t\t\t"+UI.ANSI_RED+"[Destructive Action]"+UI.ANSI_RESET+
										"Are you sure you want to delete the account "
										+ "[" +"Number Account: "+innerNumberAcc +" CPF: "+ innerClientCPF+"] [Y/N]?\n\t\t\t"+UI.ANSI_YELLOW+">>>"+UI.ANSI_RESET);
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
									break;
								}else if(answer.charAt(0) == 'N') {
									System.out.println(UI.ANSI_YELLOW+">>> Returning to the main menu..."+UI.ANSI_RESET);
									break;
								}else {
									System.out.print(UI.ANSI_RED+"\t\t\tError: there is not an option for this caracter!\n "+UI.ANSI_RESET);
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
				System.out.print("\t\t\tError: "+UI.ANSI_RED+"Caracters different from numbers are not allowed!."+UI.ANSI_RESET+"\n"
						+ "\t\t\tpress enter key to try again");
				input.nextLine();
			}catch(IndexOutOfBoundsException e) {
				System.out.println(e.getMessage() + "\n\t\t\tpress enter key to try again");
				input.nextLine();
			}catch(FileNotFoundException e) {
				UI.clearScreen();
				UI.printANSCIILogo();
				System.out.println(e.getMessage() + "\n\t\t\tpress enter key to try again");
				input.nextLine();
			} catch (InvalidOperationException e) {
				UI.clearScreen();
				UI.printANSCIILogo();
				System.out.println(e.getMessage() + "\n\t\t\tpress enter key to try again");
				input.nextLine();
			}
			UI.clearScreen();
			if(n.equals("4")) {
				break;
			}
		}
	}
}