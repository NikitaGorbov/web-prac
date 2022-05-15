package service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import bl.SessionUtil;
import dao.VacancyDAO;
import entity.Cv;
import entity.Vacancy;

public class VacancyService extends SessionUtil implements VacancyDAO {


    @Override
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
    	Session session = openSession();
        Criteria criteria = session.createCriteria(Vacancy.class);
        List<Vacancy> data = criteria.list();
        session.close();
        return data;
    }

	@Override
	public Vacancy getById(Long id) {
		Vacancy vacancy = null;
		Session session = null;
		try {
			session = openSession();
			vacancy = session.get(Vacancy.class, id);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return vacancy;
	}

	@Override
	public void update(Vacancy vacancy) {
        openTransactionSession();
        Session session = getSession();
        session.update(vacancy);
        closeTransactionSesstion();
	}

	@Override
	public void remove(Vacancy vacancy) {
        openTransactionSession();
        Session session = getSession();
        session.remove(vacancy);
        closeTransactionSesstion();
	}

	public List<Cv> getRelevantCvs(Vacancy vacancy) {
		Session session = openSession();
	    //CriteriaBuilder builder = session.getCriteriaBuilder();
		Criterion rest1 = Restrictions.ge("work_exp", vacancy.getExp_required());
		Criterion rest2 = Restrictions.le("desired_salary", vacancy.getSalary());
		Criterion rest3 = Restrictions.eq("objective", vacancy.getPosition());
	    Criteria criteria = session.createCriteria(Cv.class).add(Restrictions.and(rest1, rest2, rest3));
//	    CriteriaQuery<Cv> criteria = builder.createQuery(Cv.class);
//	    Root<Cv> root = criteria.from(Cv.class);
//	    criteria.select(root);
//	    criteria.where(builder.ge(root.get("work_exp"), vacancy.getExp_required()),
//	    			   builder.le(root.get("desired_salary"), vacancy.getSalary()),
//	    			   builder.equal(root.get("objective").get("pos_id"), vacancy.getPosition().getPos_id()));
	    List<Cv> data = criteria.list();
	    session.close();
	    return data;
	}

}
