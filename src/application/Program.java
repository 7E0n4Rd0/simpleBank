package application;

import model.dao.AgencyDao;
import model.dao.DaoFactory;
import model.entities.Agency;


public class Program {
	
	public static void main(String[] args){
		
		
		System.out.println("=== TEST 01 AGENCY: findByCode ===");
		AgencyDao agencyDao = DaoFactory.createAgencyDao();
		Agency agency = agencyDao.findByCode("2834");
		System.out.println(agency);
		
	}
}