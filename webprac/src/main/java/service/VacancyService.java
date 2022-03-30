package service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

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
	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<Vacancy> criteria = builder.createQuery(Vacancy.class);
	    criteria.from(Vacancy.class);
	    List<Vacancy> data = session.createQuery(criteria).getResultList();
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
	    CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<Cv> criteria = builder.createQuery(Cv.class);
	    Root<Cv> root = criteria.from(Cv.class);
	    criteria.select(root);
	    criteria.where(builder.ge(root.get("work_exp"), vacancy.getExp_required()),
	    			   builder.le(root.get("desired_salary"), vacancy.getSalary()),
	    			   builder.equal(root.get("objective").get("pos_id"), vacancy.getPosition().getPos_id()));
	    List<Cv> data = session.createQuery(criteria).getResultList();
	    session.close();
	    return data;
	}

}
