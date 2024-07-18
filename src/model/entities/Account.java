package model.entities;

public class Account {
	private Integer numberAccount;
	private Integer passwordAccount;
	private Double balance;
	private Client client;
	
	
	public Account() {
	}

	public Account(Integer numberAccount, Integer passwordAccount, Client client) {
		this.client = client;
		this.numberAccount = numberAccount;
		this.passwordAccount = passwordAccount;
		this.balance = 0.0;
	}

	public Integer getNumberAccount() {
		return numberAccount;
	}
	
	public Integer getPasswordAccount() {
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
		sb.append("Number accoount: " + getNumberAccount() + "\n");
		sb.append("password: " + getPasswordAccount() + "\n");
		sb.append("Balance: " + getBalance() + "\n");
		sb.append("Client: " + getClient() + "\n");
		return sb.toString();
	}
}