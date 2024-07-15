package model.entities;

public class Client {
	private String cpfClient;
	private String nameClient;
	private String phoneNumberClient;
	
	public Client() {
	}

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
	public String toString() {
		return getCpfClient() + " " + getNameClient() + " " + getPhoneNumberClient();
	}
}
