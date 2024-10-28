package application;

import java.util.List;

import model.dao.AccountDao;
import model.dao.AgencyDao;
import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Account;
import model.entities.Agency;
import model.entities.Client;


public class Program {
	
	public static void main(String[] args){
		
		AgencyDao agencyDao = DaoFactory.createAgencyDao();
		ClientDao clientDao = DaoFactory.createClientDao();
		AccountDao accountDao = DaoFactory.createAccount();
		
		System.out.println("=== TEST 01 Account : findAll ===");
		List<Account> accounts = accountDao.findAll();
		accounts.forEach(System.out::println);
		
		System.out.println("=== TEST 02 Account : findByCPF ===");
		Account account = accountDao.findByCPF("152.205.284-40");
		System.out.println(account);
		
		System.out.println("=== TEST 03 Account : findByNumberAccount ===");
		account = accountDao.findByNumberAccount("76391465");
		System.out.println(account);
		
		System.out.println("=== TEST 04 Account : insert ===");
		Agency agency = agencyDao.findByCode("4265");
		clientDao.insert(new Client("983.279.841-00", "Roberto Carlos", "(11) 97382-6428"));
		clientDao.insert(new Client("980.435.841-01", "Bruce Wayne", "(11) 99928-2534"));
		Account newAccount = new Account("09824921", "1343", agency, clientDao.findByCPF("983.279.841-00"));
		Account newAccount1 = new Account("02809171", "0889", agency, clientDao.findByCPF("980.435.841-01"));
		accountDao.insert(newAccount);
		accountDao.insert(newAccount1);
		System.out.println(newAccount);
		System.out.println(newAccount1);
		
		System.out.println("=== TEST 05 Account : update ===");
		account = accountDao.findByNumberAccount("76391465");
		account.setBalance(10932.50);
		accountDao.update(account);
		System.out.println("Updated with sucessfully");
		System.out.println(account);
		System.out.println("=== TEST 06 Account : deleteByNumberAccount ===");
		accountDao.deleteByNumberAccount("02809171");
		System.out.println("=== TEST 07 Account : deleteByCPF");
		accountDao.deleteByCPF("983.279.841-00");
		
		
		
	}
}