package aplication;

import model.entities.Account;

public class UI {
	public static void printASCIILogo() {
		StringBuilder ASCIILogo = new StringBuilder();
		ASCIILogo.append(
				  "           /$$                         /$$           /$$$$$$$                      /$$      \r\n"
				+ "          |__/                        | $$          | $$__  $$                    | $$      \r\n"
				+ "  /$$$$$$$ /$$ /$$$$$$/$$$$   /$$$$$$ | $$  /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$\r\n"
				+ " /$$_____/| $$| $$_  $$_  $$ /$$__  $$| $$ /$$__  $$| $$$$$$$  |____  $$| $$__  $$| $$  /$$/\r\n"
				+ "|  $$$$$$ | $$| $$ \\ $$ \\ $$| $$  \\ $$| $$| $$$$$$$$| $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/ \r\n"
				+ " \\____  $$| $$| $$ | $$ | $$| $$  | $$| $$| $$_____/| $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$ \r\n"
				+ " /$$$$$$$/| $$| $$ | $$ | $$| $$$$$$$/| $$|  $$$$$$$| $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$\r\n"
				+ "|_______/ |__/|__/ |__/ |__/| $$____/ |__/ \\_______/|_______/  \\_______/|__/  |__/|__/  \\__/\r\n"
				+ "                            | $$                                                            \r\n"
				+ "                            | $$                                                            \r\n"
				+ "                            |__/                                                            \r\n"
				+ "\r\n"
				+ "");
		System.out.println(ASCIILogo.toString());
	}
	
	public static void printHelloMessage(Account account) {
		StringBuilder welcomeMessage = new StringBuilder();
		welcomeMessage.append("Hello, " + account.getClient().getNameClient() + 
				". Thanks to open your account in the simpleBank.\n" +
				"The number of your account is " + account.getNumberAccount() + 
				" and password is " + account.getPasswordAccount() +". Don't forget it.\n");
		System.out.println(welcomeMessage.toString());
	}
	
	public static void printBankFunctionsMessage() {
		StringBuilder functionsMessage = new StringBuilder();
		functionsMessage.append("\nWhat you would like to do today? \n"
				+ "[1] withdraw\n[2] deposit\n[3] check balance\n[4] exit");
		System.out.println(functionsMessage.toString());
	}
}
