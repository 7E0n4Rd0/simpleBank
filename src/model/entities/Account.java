package model.entities;

public class Account {
	private Integer numberAccount;
	private Double balance;
	private Client client;
	
	
	public Account() {
	}

	public Account(Integer numberAccount, Double balance, Client client) {
		this.client = client;
		this.numberAccount = numberAccount;
		this.balance = balance;
	}

	public Integer getNumberAccount() {
		return numberAccount;
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Number accoount: " + getNumberAccount() + "\n");
		sb.append("Balance: " + getBalance() + "\n");
		sb.append("Client: " + getClient() + "\n");
		return sb.toString();
	}
}
