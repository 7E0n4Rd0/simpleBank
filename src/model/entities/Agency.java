package model.entities;

import java.util.HashSet;
import java.util.Set;

public class Agency {
	private String agencyCode;
	private String agencyAddress;
	private Set<Account> accounts = new HashSet<Account>();
	
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
	
	public Set<Account> getAccountsList(){
		return accounts;
	}
	
	@Override
	public String toString() {
		return " Agency code: " + String.format("%03d", getAgencyCode()) + 
				" Agency address: " + getAgencyAddress();
	}
	
}