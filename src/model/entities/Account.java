package model.entities;

import java.util.Objects;

public class Account {
	private String numberAccount;
	private String passwordAccount;
	private Double balance;
	private Client client;
	private Agency agency;
	
	public Account(String numberAccount, String passwordAccount, Agency agency, Client client) {
		this.agency = agency;
		this.numberAccount = numberAccount;
		this.passwordAccount = passwordAccount;
		this.client = client;
		this.balance = 0.0;
	}
	
	public Account() {}
	
	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public void setNumberAccount(String numberAccount) {
		this.numberAccount = numberAccount;
	}

	public void setPasswordAccount(String passwordAccount) {
		this.passwordAccount = passwordAccount;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Agency getAgency() {
		return agency;
	}
	
	public String getNumberAccount() {
		return numberAccount;
	}
	
	public String getPasswordAccount() {
		return passwordAccount;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public Client getClient() {
		return client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, numberAccount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(client, other.client) && Objects.equals(numberAccount, other.numberAccount);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Agency Code: " + agency.getAgencyCode() + ", ");
		sb.append("Number account: " + getNumberAccount() + ", ");
		sb.append("password: " + getPasswordAccount() + ", ");
		sb.append("Balance: " + String.format("%.2f",getBalance()) + ", ");
		sb.append(getClient());
		return sb.toString();
	}
}