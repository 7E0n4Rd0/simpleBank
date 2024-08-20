package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Agency {
	private String agencyCode;
	private String agencyAddress;
	private List<Account> accounts = new ArrayList<>();
	
	public Agency(String agencyCode, String agencyAddress) {
		this.agencyCode = agencyCode;
		this.agencyAddress = agencyAddress;
	}

	public String getAgencyAddress() {
		return agencyAddress;
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}

	public String getAgencyCode() {
		return agencyCode;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void removeAccount(Account account) {
		accounts.remove(account);
	}
	
	public List<Account> getAccountsList(){
		return accounts;
	}
	
	@Override
	public String toString() {
		return " Agency code: " + String.format("%03d", getAgencyCode()) + 
				" Agency address: " + getAgencyAddress();
	}
	
}