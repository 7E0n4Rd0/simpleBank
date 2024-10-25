package model.dao;

import db.DB;
import model.dao.impl.AgencyDaoJDBC;

public class DaoFactory {
	
	public static AgencyDao createAgencyDao() {
		return new AgencyDaoJDBC(DB.getConnection());
	}
	
}
