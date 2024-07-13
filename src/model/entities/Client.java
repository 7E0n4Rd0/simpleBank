package model.entities;

public class Client {
	private String cpfClient;
	private String nameClient;
	private Integer phoneNumberClient;
	
	public Client() {
	}

	public Client(String cpfClient, String nameClient, Integer phoneNumberClient) {
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

	public Integer getPhoneNumberClient() {
		return phoneNumberClient;
	}

	public void setPhoneNumberClient(Integer phoneNumberClient) {
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
