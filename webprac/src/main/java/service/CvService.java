package service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;

import bl.SessionUtil;
import dao.CvDAO;
import entity.Cv;
import entity.Vacancy;

public class CvService extends SessionUtil implements CvDAO {


    @Override
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
    	Session session = openSession();
        Criteria criteria = session.createCriteria(Cv.class);
        List<Cv> data = criteria.list();
        session.close();
        return data;
    }

	@Override
	public Cv getById(Long id) {
		Cv cv = null;
		Session session = null;
		try {
			session = openSession();
			cv = session.get(Cv.class, id);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return cv;
	}

	@Override
	public void update(Cv cv) {
        openTransactionSession();
        Session session = getSession();
        session.update(cv);
        closeTransactionSesstion();
	}

	@Override
	public void remove(Cv cv) {
        openTransactionSession();
        Session session = getSession();
        session.remove(cv);
        closeTransactionSesstion();
	}

	public List<Vacancy> getRelevantVacancies(Cv cv) {
		Session session = openSession();
	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<Vacancy> criteria = builder.createQuery(Vacancy.class);
	    Root<Vacancy> root = criteria.from(Vacancy.class);
	    criteria.select(root);
	    criteria.where(builder.le(root.get("exp_required"), cv.getWork_exp()),
	    			   builder.ge(root.get("salary"), cv.getDesired_salary()),
	    			   builder.equal(root.get("position").get("pos_id"), cv.getObjective().getPos_id()));
	    List<Vacancy> data = session.createQuery(criteria).getResultList();
	    session.close();
	    return data;
	}

}
