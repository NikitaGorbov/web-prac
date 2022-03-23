package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Company;

public interface CompanyDAO {
	void add(Company company) throws SQLException;
	List<Company> getAll();
	Company getById(int id);
	void update(Company company);
	void remove(Company company);
}
