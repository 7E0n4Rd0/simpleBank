package aplication;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Account;
import model.entities.Agency;
import model.excpetion.InvalidOperationException;
import model.services.OtherService;
import model.services.RegistrationService;

public class Program {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		Scanner input = new Scanner(System.in);
		
		Agency agency = RegistrationService.registerAgency();
		Account account = RegistrationService.registerAccount(agency);
		
		StringBuilder welcomeMessage = new StringBuilder();
		welcomeMessage.append("Hello, " + account.getClient().getNameClient() + 
				". Thanks to open your account in the simpleBank.\n" +
				"The number of your account is " + account.getNumberAccount() + 
				" and password is " + account.getPasswordAccount() +". Don't forget it.\n");

		System.out.println(welcomeMessage);
		
		while(true) {
			try {	
				while(true) {
					System.out.println("\nWhat you would like to do today? ");
					System.out.println("[1] withdraw\n[2] deposit\n[3] check balance\n[4] exit");
					double amount = 0;
					int answer = input.nextInt();
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
						break;
					}
				}
				break;
			}catch(InputMismatchException e) {
				System.out.println("Error: Only numbers will be accepted.");
			}catch(InvalidOperationException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
//Finished at a wednesday on july seventeen 21:56. 
//I'll continue to I will continue to maintain this little project. - Leo