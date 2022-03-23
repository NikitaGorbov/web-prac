package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Education;

public interface EducationDAO {
	void add(Education education) throws SQLException;
	List<Education> getAll();
	Education getById(int id);
	void update(Education education);
	void remove(Education education);
}
