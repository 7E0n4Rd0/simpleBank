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
import model.dao.ClientDao;
import model.entities.Client;

public class ClientDaoJDBC implements ClientDao{

	private Connection connection;
	
	public ClientDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void insert(Client obj) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO Client(CPFClient, nameClient, phoneNumberClient) "
					+ "VALUE (?, ?, ?)");
			preparedStatement.setString(1, obj.getCpfClient());
			preparedStatement.setString(2, obj.getNameClient());
			preparedStatement.setString(3, obj.getPhoneNumberClient());
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new DBException("Can't add the same Client already registered.");
			}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void update(Client obj) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE Client SET nameClient = ?, phoneNumberClient = ? "
					+ "WHERE CPFClient = ?");
			preparedStatement.setString(1, obj.getNameClient());
			preparedStatement.setString(2, obj.getPhoneNumberClient());
			preparedStatement.setString(3, obj.getCpfClient());
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new DBException("Couldn't update Client, something went wrong.");
			}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		
	}

	@Override
	public void deleteByCPF(String cpf) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"DELETE FROM Client WHERE CPFClient = ?");
			preparedStatement.setString(1, cpf);
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) {
				throw new DBException("Client does not exists");
			}
		}catch(SQLException e) {
			throw new DBIntegrityException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public Client findByCPF(String cpf) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM Client WHERE CPFClient = ?");
			preparedStatement.setString(1, cpf);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				Client client = instantiateClient(resultSet);
				return client;
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
	public List<Client> findAll() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT * FROM Client");
			resultSet = preparedStatement.executeQuery();
			List<Client> clients = new ArrayList<>();
			while(resultSet.next()) {
				clients.add(instantiateClient(resultSet));
			}
			return clients;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
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
