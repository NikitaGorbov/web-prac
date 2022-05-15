package service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import bl.SessionUtil;
import dao.CompanyDAO;
import entity.Company;

public class CompanyService extends SessionUtil implements CompanyDAO {


    @Override
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
    	Session session = openSession();
        Criteria criteria = session.createCriteria(Company.class);
        List<Company> data = criteria.list();
        session.close();
        return data;
    }

	@Override
	public Company getById(Long id) {
		Company company = null;
		Session session = null;
		try {
			session = openSession();
			company = session.get(Company.class, id);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return company;
	}

	@Override
	public void update(Company company) {
        openTransactionSession();
        Session session = getSession();
        session.update(company);
        closeTransactionSesstion();
	}

	@Override
	public void remove(Company company) {
        openTransactionSession();
        Session session = getSession();
        session.remove(company);
        closeTransactionSesstion();
	}

}
