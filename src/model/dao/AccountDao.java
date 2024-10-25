package model.dao;

import java.util.List;

import model.entities.Account;

public interface AccountDao {
	void insert(Account obj);
	void update(Account obj);
	void deleteById(Integer id);
	void deleteByCPF(String cpf);
	Account findById(Integer id);
	Account findByCPF(String cpf);
	List<Account> findAll();
}
