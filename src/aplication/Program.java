package aplication;

import model.entities.Account;
import model.entities.Agency;
import model.services.RegistrationService;

public class Program {

	public static void main(String[] args) {
		
		Agency agency = RegistrationService.registerAgency();
		Account account = RegistrationService.registerAccount(agency);
		StringBuilder welcomeMessage = new StringBuilder();
		welcomeMessage.append("Hello, " + account.getClient().getNameClient() + 
				". Thanks to open your account in the simpleBank.\n");
		welcomeMessage.append("The number of your account is " + account.getNumberAccount() + 
				" and password is " + account.getPasswordAccount() +". Don't forget it.");
		System.out.println(welcomeMessage);
		
		
		
	}
	
}
