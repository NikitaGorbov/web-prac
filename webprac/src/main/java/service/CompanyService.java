package service;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.CompanyDAO;
import entity.Applicant;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyService extends SessionUtil implements CompanyDAO {

	
    public void add(Company company) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(company);

        //close session with a transaction
        closeTransactionSesstion();
    }

	@Override
	public List<Company> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getById(Long id) {
		Company company = null;
		Session session = null;
		try {
			session = openSession();
			company = (Company) session.get(Company.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return company;
	}

	@Override
	public void update(Company company) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Company company) {
		// TODO Auto-generated method stub
		
	}

}
