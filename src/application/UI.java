package application;

import model.entities.Account;

public class UI {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	
	public static void printANSCIILogo() {
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
		options.append("\t\t\t[1] Register Agency\n"
				+ "\t\t\t[2] Register an Account\n"
				+ "\t\t\t[3] Delete an Account\n"
				+ "\t\t\t[4] Exit\n"
				+ANSI_GREEN+"\t\t\t\t>>> "+ANSI_RESET);
		System.out.print(options.toString());
	}
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
