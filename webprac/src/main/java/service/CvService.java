package service;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.CvDAO;
import entity.Cv;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CvService extends SessionUtil implements CvDAO {

	
    public void add(Cv cv) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(cv);

        //close session with a transaction
        closeTransactionSesstion();
    }

	@Override
	public List<Cv> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cv getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cv cv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Cv cv) {
		// TODO Auto-generated method stub
		
	}

}
