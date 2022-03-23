package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Applicant;

public interface ApplicantDAO {
	void add(Applicant company) throws SQLException;
	List<Applicant> getAll();
	Applicant getById(int id);
	void update(Applicant company);
	void remove(Applicant company);
}
