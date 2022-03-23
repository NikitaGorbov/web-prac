package service;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.Previous_job_recordDAO;
import entity.Previous_job_record;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Previous_job_recordService extends SessionUtil implements Previous_job_recordDAO {

	
    public void add(Previous_job_record previous_job_record) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(previous_job_record);

        //close session with a transaction
        closeTransactionSesstion();
    }

	@Override
	public List<Previous_job_record> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Previous_job_record getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Previous_job_record previous_job_record) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Previous_job_record previous_job_record) {
		// TODO Auto-generated method stub
		
	}

}
