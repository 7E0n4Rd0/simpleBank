package model.entities;

public class Account {
	private Integer agencyCode;
	private Integer numberAccount;
	private String passwordAccount;
	private Double balance;
	private Client client;
	
	public Account(Integer agencyCode, Integer numberAccount, String passwordAccount, Client client) {
		this.agencyCode = agencyCode;
		this.numberAccount = numberAccount;
		this.passwordAccount = passwordAccount;
		this.client = client;
		this.balance = 0.0;
	}
	
	public Integer getAgencyCode() {
		return agencyCode;
	}
	
	public Integer getNumberAccount() {
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Agency Code: " + getAgencyCode() + "; ");
		sb.append("Number account: " + getNumberAccount() + "; ");
		sb.append("password: " + getPasswordAccount() + "; ");
		sb.append("Balance: " + String.format("%.2f",getBalance()) + "; ");
		sb.append(getClient());
		return sb.toString();
	}
}