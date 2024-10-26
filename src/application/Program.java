package application;

import java.util.List;

import model.dao.AgencyDao;
import model.dao.DaoFactory;
import model.entities.Agency;


public class Program {
	
	public static void main(String[] args){
		
		AgencyDao agencyDao = DaoFactory.createAgencyDao();
		System.out.println("=== TEST 01 AGENCY: findByCode ===");
		Agency agency = agencyDao.findByCode("2834");
		System.out.println(agency);
		
		System.out.println("=== TEST 02 AGENCY: findAll ===");
		List<Agency> agencys = agencyDao.findAll();
		agencys.forEach(System.out::println);
		
		System.out.println("=== TEST 03 AGENCY: insert ===");
		Agency newAgency = new Agency("0983", "Rua São Miguel");
		agencyDao.insert(newAgency);
		agencys = agencyDao.findAll();
		agencys.forEach(System.out::println);
		
		System.out.println("=== TEST 04 AGENCY: update ===");
		agency = agencyDao.findByCode("0983");
		agency.setAgencyAddress("Avenida Santos de Paula");
		agencyDao.update(agency);
		System.out.println(agency);
		
		System.out.println("=== TEST 05 AGENCY: deleteByCode ===");
		agencyDao.deleteByCode("0983");
		System.out.println("Deleted with sucessfully");
		agencys = agencyDao.findAll();
		agencys.forEach(System.out::println);
		
		
		
	}
}