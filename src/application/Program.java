package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Agency;
import model.services.OtherService;
import model.services.RegistrationService;

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
					if(number > 3 || number <= 0) {
						throw new IndexOutOfBoundsException();
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
							String line = br.readLine();
							String[] fields = line.split(",");
							RegistrationService.registerAccount(new Agency(Integer.parseInt(fields[0]), fields[1]));
						}catch (IOException e) {
							System.out.println("Error: " + e.getMessage());
						}
						if(!fileExists) {
							throw new FileNotFoundException();
						}
					case 3:
						System.out.println("\t\t\tBye bye");
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
				System.out.print("\t\t\tError: there is not an option for this number!\n \t\t\tpress enter key to try again");
				input.nextLine();
			}catch(FileNotFoundException e) {
				UI.clearScreen();
				UI.printANSCIILogo();
				System.out.println("\t\t\t" + UI.ANSI_RED + "Fatal Error: Couldn't found the agencys.csv file" + UI.ANSI_RESET 
						+ "\n \t\t\tpress enter key to try again");
				input.nextLine();
			}
			UI.clearScreen();
			if(n.equals("3")) {
				break;
			}
		}
		
		/*
		This is the ATM service, I'll move this later.
		
		while(true) {
			try {	
				while(true) {
					UI.printASCIILogo();
					UI.printHelloMessage(account);
					UI.printBankFunctionsOptions();
					double amount = 0;
					int answer = Integer.parseInt(input.nextLine());
					switch(answer) {
					case 1:
						while(true) {
							System.out.println("Withdraw");
							OtherService.login(agency);
							break;
						}
						while(true) {
							System.out.print("Enter the amount to withdraw: $");
							amount = input.nextDouble();
							try {
								if(amount <= 0.0) {
									throw new IllegalArgumentException("You can't withdraw $"+ String.format("%.2f", amount));
								}if(account.getBalance() < amount) {
									throw new IllegalArgumentException("You can't withdraw an amount that is highier than your balance.");
								}
								account.withdraw(amount);
								account.checkBalance();
								break;
							}catch(IllegalArgumentException e) {
								System.out.println("Error: " + e.getMessage());
							}catch(InputMismatchException e) {
								System.out.println("Error: was expected to only type numbers");
							}
						}
					break;
					
					case 2:
						while(true) {
							System.out.println("Deposit");
							OtherService.login(agency);
							break;
						}
						while(true) {
							System.out.print("Enter the amount to deposit: $");
							amount = input.nextDouble();
							try {
								if(amount <= 0.0) {
									throw new IllegalArgumentException("You can't deposit $"+ String.format("%.2f", amount));
								}
								account.deposit(amount);
								account.checkBalance();
								break;
							}catch(IllegalArgumentException e) {
								System.out.println("Error: " + e.getMessage());
							}catch(InputMismatchException e) {
								System.out.println("Error: was expected to only type numbers");
							}
						}
					break;
					
					case 3:
						while(true) {
							System.out.println("Check Balance");
							OtherService.login(agency);
							break;
						}
						account.checkBalance();
					break;
					
					case 4:
						System.out.println("Bye bye!!");
					break;
					default: 
						throw new InvalidOperationException("This operation does not exist!");
					}
					if(answer == 4) {
						input.close();
						break;
					}
				}
				break;
			}catch(InputMismatchException e) {
				System.out.println("Error: Caracters different from numbers are not allowed!.");
			}catch(InvalidOperationException e) {
				System.out.println("Error: " + e.getMessage());
			}	
		}
		*/
	}
}