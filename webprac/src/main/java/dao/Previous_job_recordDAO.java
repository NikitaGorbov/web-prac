package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Previous_job_record;

public interface Previous_job_recordDAO {
	void add(Previous_job_record previous_job_record) throws SQLException;
	List<Previous_job_record> getAll();
	Previous_job_record getById(int id);
	void update(Previous_job_record previous_job_record);
	void remove(Previous_job_record previous_job_record);
}
