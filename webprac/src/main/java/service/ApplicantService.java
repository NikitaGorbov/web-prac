package service;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.ApplicantDAO;
import entity.Applicant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicantService extends SessionUtil implements ApplicantDAO {

	
    public void add(Applicant applicant) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(applicant);

        //close session with a transaction
        closeTransactionSesstion();
    }

	@Override
	public List<Applicant> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Applicant applicant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Applicant applicant) {
		// TODO Auto-generated method stub
		
	}

}
