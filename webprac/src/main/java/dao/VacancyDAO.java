package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Vacancy;

public interface VacancyDAO {
	void add(Vacancy vacancy) throws SQLException;
	List<Vacancy> getAll();
	Vacancy getById(Long id);
	void update(Vacancy vacancy);
	void remove(Vacancy vacancy);
}
