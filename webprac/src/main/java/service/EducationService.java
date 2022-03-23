package service;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.EducationDAO;
import entity.Cv;
import entity.Education;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducationService extends SessionUtil implements EducationDAO {

	
    public void add(Education education) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(education);

        //close session with a transaction
        closeTransactionSesstion();
    }

	@Override
	public List<Education> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Education getById(Long id) {
		Education education = null;
		Session session = null;
		try {
			session = openSession();
			education = (Education) session.get(Education.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return education;
	}

	@Override
	public void update(Education education) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Education education) {
		// TODO Auto-generated method stub
		
	}

}
