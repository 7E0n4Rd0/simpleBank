package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import db.DBIntegrityException;
import model.dao.AgencyDao;
import model.entities.Agency;
import model.entities.Client;

public class AgencyDaoJDBC implements AgencyDao{
	
	private Connection connection;
	
	public AgencyDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void insert(Agency obj) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO Agency(agencyCode, agencyAddress) "+
					"VALUE (? , ?)");
			preparedStatement.setString(1, obj.getAgencyCode());
			preparedStatement.setString(2, obj.getAgencyAddress());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void update(Agency obj) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE Agency SET agencyAddress = ? "
				  + "WHERE agencyCode = ?");
			preparedStatement.setString(1, obj.getAgencyAddress());
			preparedStatement.setString(2, obj.getAgencyCode());
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new DBException("Couldn't update this Agency. Something went wrong!");
			}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
		}
		
	}

	@Override
	public void deleteByCode(String code) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"DELETE FROM Agency WHERE agencyCode = ?");
			preparedStatement.setString(1, code);
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new DBException("Does not exists an Agency with the code \""+code+"\"");
			}
		}catch(SQLException e) {
			throw new DBIntegrityException(e.getMessage());
		}
		
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
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM Agency");
			resultSet = preparedStatement.executeQuery();
			List<Agency> list = new ArrayList<>();
			while(resultSet.next()) {
				list.add(instantiateAgency(resultSet));
			}
			return list;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
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
	
	private static Client instantiateClient(ResultSet resultSet) {
		Client client = new Client();
		try {
			client.setCpfClient(resultSet.getString("CPFClient"));
			client.setNameClient(resultSet.getString("nameClient"));
			client.setPhoneNumberClient(resultSet.getString("phoneNumberClient"));
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		return client;
	}
}
