package model.entities;

import java.util.Objects;

public class Account {
	private String agencyCode;
	private String numberAccount;
	private String passwordAccount;
	private Double balance;
	private Client client;
	
	public Account(String agencyCode, String numberAccount, String passwordAccount, Client client) {
		this.agencyCode = agencyCode;
		this.numberAccount = numberAccount;
		this.passwordAccount = passwordAccount;
		this.client = client;
		this.balance = 0.0;
	}
	
	public String getAgencyCode() {
		return agencyCode;
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
	
	public void deposit(Double amount) {
		this.balance += amount;
	}
	
	public void withdraw(Double amount) {
		this.balance -= amount;
	}
	public void checkBalance() {
		System.out.println("Your current is R$" + String.format("%.2f", getBalance()));
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
		sb.append("Agency Code: " + getAgencyCode() + ", ");
		sb.append("Number account: " + getNumberAccount() + ", ");
		sb.append("password: " + getPasswordAccount() + ", ");
		sb.append("Balance: " + String.format("%.2f",getBalance()) + ", ");
		sb.append(getClient());
		return sb.toString();
	}
}