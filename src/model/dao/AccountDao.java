package model.dao;

import java.util.List;

import model.entities.Account;

public interface AccountDao {
	void insert(Account obj);
	void update(Account obj);
	void deleteByNumberAccount(String numberAccount);
	void deleteByCPF(String cpf);
	Account findByNumberAccount(String numberAccount);
	Account findByCPF(String cpf);
	List<Account> findAll();
}
