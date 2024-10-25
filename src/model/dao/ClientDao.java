package model.dao;

import java.util.List;

import model.entities.Client;

public interface ClientDao {
	void insert(Client obj);
	void update(Client obj);
	void deleteByCPF(String cpf);
	Client findByCPF(String cpf);
	List<Client> findAll();
}
