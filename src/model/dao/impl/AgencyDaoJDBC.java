package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.AgencyDao;
import model.entities.Agency;

public class AgencyDaoJDBC implements AgencyDao{
	
	private Connection connection;
	
	public AgencyDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void insert(Agency obj) {
		
	}

	@Override
	public void update(Agency obj) {
		
		
	}

	@Override
	public void deleteByCode(String code) {
		
		
	}

	@Override
	public Agency findByCode(String code) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM Agency WHERE agencyCode = ?");
			preparedStatement.setString(1, String.valueOf(code));
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Agency agency = instantiateAgency(resultSet);
				return agency;
			}
			return null;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Agency> findAll() {
		
		return null;
	}
	
	
	private static Agency instantiateAgency(ResultSet resultSet) {
		Agency agency = new Agency();
		try {
			agency.setAgencyCode(resultSet.getString("agencyCode"));
			agency.setAgencyAddress(resultSet.getString("agencyAddress"));
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		
		
		
		return agency;
	}
}
