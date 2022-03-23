package service;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.VacancyDAO;
import entity.Previous_job_record;
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
	public Vacancy getById(Long id) {
		Vacancy vacancy = null;
		Session session = null;
		try {
			session = openSession();
			vacancy = (Vacancy) session.get(Vacancy.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vacancy;
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
