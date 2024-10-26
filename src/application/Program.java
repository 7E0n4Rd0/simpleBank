package application;

import java.util.List;

import model.dao.AgencyDao;
import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Agency;
import model.entities.Client;


public class Program {
	
	public static void main(String[] args){
		
		AgencyDao agencyDao = DaoFactory.createAgencyDao();
		ClientDao clientDao = DaoFactory.createClientDao();
		
		System.out.println("=== TEST 01 Client: findByCPF ===");
		Client client = clientDao.findByCPF("267.402.682-07");
		System.out.println(client);
		
		System.out.println("=== TEST 02 Clietn: findAll ===");
		List<Client> clients = clientDao.findAll();
		clients.forEach(System.out::println);
		
		System.out.println("=== TEST 03 Client: insert ===");
		Client newClient = new Client("472.098.123-00", "Machado de Assis", "(31) 98349-2024");
		clientDao.insert(newClient);
		clients = clientDao.findAll();
		clients.forEach(System.out::println);
		
		System.out.println("=== TEST 04 Client: update ===");
		client = clientDao.findByCPF("472.098.123-00");
		client.setPhoneNumberClient("(11) 93278-9328");
		clientDao.update(client);
		System.out.println(client);
		
		System.out.println("=== TEST 05 AGENCY: deleteByCPF ===");
		clientDao.deleteByCPF("472.098.123-00");
		System.out.println("Deleted with sucessfully");
		clients = clientDao.findAll();
		clients.forEach(System.out::println);
		
		
		
	}
}