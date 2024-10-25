package model.dao;

import java.util.List;

import model.entities.Agency;

public interface AgencyDao {
	void insert(Agency obj);
	void update(Agency obj);
	void deleteByCode(String code);
	Agency findByCode(String code);
	List<Agency> findAll();
}
