package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DBException;
import db.DBIntegrityException;
import model.dao.AccountDao;
import model.entities.Account;
import model.entities.Agency;
import model.entities.Client;

public class AccountDaoJDBC implements AccountDao{

	private Connection connection;
	
	public AccountDaoJDBC(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void insert(Account obj) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"INSERT INTO Account(numberAccount, passwordAccount, accountBalance, agencyCode, CPFClient) "
					+ "VALUE(? , ? , ? , ? , ?)");
			preparedStatement.setString(1, obj.getNumberAccount());
			preparedStatement.setString(2, obj.getPasswordAccount());
			preparedStatement.setDouble(3, obj.getBalance());
			preparedStatement.setString(4, obj.getAgency().getAgencyCode());
			preparedStatement.setString(5, obj.getClient().getCpfClient());
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) throw new DBException("Couldn't insert a Account, something went wrong");
	
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void update(Account obj) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"UPDATE Account SET passwordAccount = ?, accountBalance = ? "
				  + "WHERE numberAccount = ?");
			preparedStatement.setString(1, obj.getPasswordAccount());
			preparedStatement.setDouble(2, obj.getBalance());
			preparedStatement.setString(3, obj.getNumberAccount());
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) throw new DBException("Couldn't update Account, somethong went wrong");
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
		}
		
	}

	@Override
	public void deleteByNumberAccount(String numberAccount) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"DELETE Account, Client "
				  + "FROM Account "
				  + "INNER JOIN Client "
				  	+ "ON Client.CPFClient = Account.CPFClient "
				  + "WHERE Account.numberAccount = ? ");
			preparedStatement.setString(1, numberAccount);
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) throw new DBException("Couldn't delete Account, something went wrong.");
		}catch(SQLException e) {
			throw new DBIntegrityException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void deleteByCPF(String cpf) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(
					"DELETE Account, Client "
					+ "FROM Account "
					+ "INNER JOIN Client "
					+ "	ON Client.CPFClient = Account.CPFClient "
					+ "WHERE Account.CPFClient = ? ");
			preparedStatement.setString(1, cpf);
			int rowsAffected = preparedStatement.executeUpdate();
			if(rowsAffected == 0) throw new DBException("Couldn't delete Account, something went wrong.");
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
		}
		
	}

	@Override
	public Account findByNumberAccount(String numberAccount) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT Agency.*, Account.*, Client.* "
					+ "FROM Account "
					+ "INNER JOIN Agency "
					+ "	ON Account.agencyCode = Agency.agencyCode "
					+ "INNER JOIN Client "
					+ "	ON Account.CPFClient = Client.CPFClient "
					+ "WHERE Account.numberAccount = ?"
					);
			preparedStatement.setString(1, numberAccount);
			resultSet = preparedStatement.executeQuery();
			Account account = null;
			Map<String, Agency> mapAgency = new HashMap<String, Agency>();
			Map<String, Client> mapClient = new HashMap<String, Client>();
			if(resultSet.next()) {
				Agency agency = mapAgency.get(resultSet.getString("agencyCode"));
				Client client = mapClient.get(resultSet.getString("CPFClient"));
				if(agency == null && agency == null) {
					agency = instantiateAgency(resultSet);
					client = instantiateClient(resultSet);
				}
				account = instantiateAccount(resultSet, agency, client);
			}
			return account;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public Account findByCPF(String cpf) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT Agency.*, Account.*, Client.* "
					+ "FROM Account "
					+ "INNER JOIN Agency "
					+ "	ON Account.agencyCode = Agency.agencyCode "
					+ "INNER JOIN Client "
					+ "	ON Account.CPFClient = Client.CPFClient "
					+ "WHERE Account.CPFClient = ?"
					);
			preparedStatement.setString(1, cpf);
			resultSet = preparedStatement.executeQuery();
			Account account = null;
			Map<String, Agency> mapAgency = new HashMap<String, Agency>();
			Map<String, Client> mapClient = new HashMap<String, Client>();
			while(resultSet.next()) {
				Agency agency = mapAgency.get(resultSet.getString("agencyCode"));
				Client client = mapClient.get(resultSet.getString("CPFClient"));
				if(agency == null && agency == null) {
					agency = instantiateAgency(resultSet);
					client = instantiateClient(resultSet);
				}
				account = instantiateAccount(resultSet, agency, client);
			}
			return account;
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	@Override
	public List<Account> findAll() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(
					"SELECT Agency.*, Account.*, Client.* "
					+ "FROM Account "
					+ "INNER JOIN Agency "
					+ "	ON Account.agencyCode = Agency.agencyCode "
					+ "INNER JOIN Client "
					+ "	ON Account.CPFClient = Client.CPFClient "
					+ "ORDER BY Client.nameClient"
					);
			resultSet = preparedStatement.executeQuery();
			List<Account> accounts = new ArrayList<>();
			Map<String, Agency> mapAgency = new HashMap<String, Agency>();
			Map<String, Client> mapClient = new HashMap<String, Client>();
			while(resultSet.next()) {
				Agency agency = mapAgency.get(resultSet.getString("agencyCode"));
				Client client = mapClient.get(resultSet.getString("CPFClient"));
				if(agency == null && agency == null) {
					agency = instantiateAgency(resultSet);
					client = instantiateClient(resultSet);
				}
				accounts.add(instantiateAccount(resultSet, agency, client));
			}
			return accounts;
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
	
	private static Account instantiateAccount(ResultSet resultSet, Agency agency, Client client) {
		Account account = new Account();
		agency = instantiateAgency(resultSet);
		client = instantiateClient(resultSet);
		try {
			account.setAgency(agency);
			account.setClient(client);
			account.setNumberAccount(resultSet.getString("numberAccount"));
			account.setPasswordAccount(resultSet.getString("passwordAccount"));
			account.setBalance(resultSet.getDouble("accountBalance"));
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		return account;
	}
}
