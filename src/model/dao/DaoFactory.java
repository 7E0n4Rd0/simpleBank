package model.dao;

import db.DB;
import model.dao.impl.AgencyDaoJDBC;
import model.dao.impl.ClientDaoJDBC;

public class DaoFactory {
	
	public static AgencyDao createAgencyDao() {
		return new AgencyDaoJDBC(DB.getConnection());
	}
	
	public static ClientDao createClientDao() {
		return new ClientDaoJDBC(DB.getConnection());
	}
	
}
