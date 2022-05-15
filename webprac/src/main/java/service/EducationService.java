package service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import bl.SessionUtil;
import dao.EducationDAO;
import entity.Education;

public class EducationService extends SessionUtil implements EducationDAO {


    @Override
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
    	Session session = openSession();
        Criteria criteria = session.createCriteria(Education.class);
        List<Education> data = criteria.list();
        session.close();
        return data;
    }

	@Override
	public Education getById(Long id) {
		Education education = null;
		Session session = null;
		try {
			session = openSession();
			education = session.get(Education.class, id);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return education;
	}

	@Override
	public void update(Education education) {
        openTransactionSession();
        Session session = getSession();
        session.update(education);
        closeTransactionSesstion();
	}

	@Override
	public void remove(Education education) {
        openTransactionSession();
        Session session = getSession();
        session.remove(education);
        closeTransactionSesstion();
	}

}
