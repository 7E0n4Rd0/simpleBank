package model.entities;

import java.util.Objects;

public class Client {
	private String cpfClient;
	private String nameClient;
	private String phoneNumberClient;
	
	public Client(String cpfClient, String nameClient, String phoneNumberClient) {
		this.cpfClient = cpfClient;
		this.nameClient = nameClient;
		this.phoneNumberClient = phoneNumberClient;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getPhoneNumberClient() {
		return phoneNumberClient;
	}

	public void setPhoneNumberClient(String phoneNumberClient) {
		this.phoneNumberClient = phoneNumberClient;
	}

	public String getCpfClient() {
		return cpfClient;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfClient);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(cpfClient, other.cpfClient);
	}

	@Override
	public String toString() {
		return "CPF: " + getCpfClient() + ", Name: " + getNameClient() + ", Phone Number: " + getPhoneNumberClient();
	}
}