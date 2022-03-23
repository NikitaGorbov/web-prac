package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Cv;

public interface CvDAO {
	void add(Cv cv) throws SQLException;
	List<Cv> getAll();
	Cv getById(Long id);
	void update(Cv cv);
	void remove(Cv cv);
}
