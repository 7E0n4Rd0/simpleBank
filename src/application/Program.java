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
import model.excpetion.InvalidDataException;
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
					if(number > 4 || number <= 0) {
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
						/*System.out.println("\t\t\tInform the account data");
						System.out.println("\t\t\tNumber Account: ");
						try {
							String numberAcc = input.nextLine();
							OtherService.isNumber(numberAcc);
						}catch(NumberFormatException e) {
							System.out.println(e.getMessage());
						}
						System.out.println("\t\t\tAccount Password: ");
						try {
							String passwordAcc = input.nextLine();
							OtherService.isNumber(passwordAcc);
						}catch(NumberFormatException e) {
							System.out.println(e.getMessage());
						}*/
						String fileAccspath = "C:/Users/leome/Desktop/Programming/Java/ws-eclipse/simpleBank/Files/accounts.csv";
						File fileAccs = new File(fileAccspath);
						OtherService.loadAccountList(fileAccs);
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
			if(n.equals("4")) {
				break;
			}
		}
	}
}