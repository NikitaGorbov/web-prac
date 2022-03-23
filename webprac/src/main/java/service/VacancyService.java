package service;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.VacancyDAO;
import entity.Vacancy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacancyService extends SessionUtil implements VacancyDAO {

	
    public void add(Vacancy vacancy) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(vacancy);

        //close session with a transaction
        closeTransactionSesstion();
    }

	@Override
	public List<Vacancy> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vacancy getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Vacancy vacancy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Vacancy vacancy) {
		// TODO Auto-generated method stub
		
	}

}
