package application;

import model.entities.Account;

public class UI {
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	
	private static void printANSCIILogo() {
		StringBuilder ANSCIILogo = new StringBuilder();
		ANSCIILogo.append(
				  "           /$$                         /$$           /$$$$$$$                      /$$      \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "          |__/                        | $$          | $$__  $$                    | $$      \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "  /$$$$$$$ /$$ /$$$$$$/$$$$   /$$$$$$ | $$  /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$\r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ " /$$_____/| $$| $$_  $$_  $$ /$$__  $$| $$ /$$__  $$| $$$$$$$  |____  $$| $$__  $$| $$  /$$/\r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "|  $$$$$$ | $$| $$ \\ $$ \\ $$| $$  \\ $$| $$| $$$$$$$$| $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/ \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ " \\____  $$| $$| $$ | $$ | $$| $$  | $$| $$| $$_____/| $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$ \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ " /$$$$$$$/| $$| $$ | $$ | $$| $$$$$$$/| $$|  $$$$$$$| $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$\r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "|_______/ |__/|__/ |__/ |__/| $$____/ |__/ \\_______/|_______/  \\_______/|__/  |__/|__/  \\__/\r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "                            | $$                                                            \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "                            | $$                                                            \r\n".replace("$", "\u001B[32m$\u001B[0m")
				+ "                            |__/                                                            \r\n"
				+ "\r\n"
				+ "");
		System.out.println(ANSCIILogo.toString());
	}
	
	public static void mainMenu() {
		StringBuilder options = new StringBuilder();
		UI.printANSCIILogo();
		options.append("				[1] Register Agency\n				"
									 + "[2] Register an Account\n" 
						+"				>>> ");
		System.out.print(options.toString());
	}
	
	public static void printHelloMessage(Account account) {
		StringBuilder welcomeMessage = new StringBuilder();
		welcomeMessage.append("Hello, " + account.getClient().getNameClient() + 
				". Thanks to open your account in the simpleBank.\n" +
				"The number of your account is " + account.getNumberAccount() + 
				" and password is " + account.getPasswordAccount() +". Don't forget it.\n");
		System.out.println(welcomeMessage.toString());
	}
	
	public static void printBankFunctionsOptions() {
		StringBuilder functionsMessage = new StringBuilder();
		functionsMessage.append("\nWhat you would like to do today? \n"
				+ "[1] withdraw\n[2] deposit\n[3] check balance\n[4] exit");
		System.out.println(functionsMessage.toString());
	}
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
