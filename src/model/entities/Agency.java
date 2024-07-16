package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Agency {
	private Integer agencyCode;
	private String bankName;
	private String agencyAddress;
	private List<Account> accounts = new ArrayList<>();
	
	public Agency() {
	}

	public Agency(Integer agencyCode, String bankName, String agencyAddress) {
		this.agencyCode = agencyCode;
		this.bankName = bankName;
		this.agencyAddress = agencyAddress;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAgencyAddress() {
		return agencyAddress;
	}

	public void setAgencyAddress(String agencyAddress) {
		this.agencyAddress = agencyAddress;
	}

	public Integer getAgencyCode() {
		return agencyCode;
	}
	
	public void setAgencyCode(Integer agencyCode) {
		this.agencyCode = agencyCode;
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
		return "Bank name: " + getBankName() + 
				" Agency code: " + getAgencyCode() + 
				" Agency address: " + getAgencyAddress();
	}
	
}
