package model.dao;

import java.util.List;

import model.entities.Agency;

public interface AgencyDao {
	void insert(Agency obj);
	Agency update(Agency obj);
	void deleteByCode(Integer code);
	Agency findByCode(Integer code);
	List<Agency> findAll();
}
