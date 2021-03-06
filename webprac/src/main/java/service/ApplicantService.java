package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import bl.SessionUtil;
import dao.ApplicantDAO;
import entity.Applicant;
import entity.Company;
import entity.Previous_job_record;

public class ApplicantService extends SessionUtil implements ApplicantDAO {


    @Override
	public void add(Applicant applicant) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(applicant);

        //close session with a transaction
        closeTransactionSesstion();
    }

    @Override
    public List<Applicant> getAll() {
    	Session session = openSession();
        Criteria criteria = session.createCriteria(Applicant.class).addOrder(Order.asc("appl_name"));
        List<Applicant> data = criteria.list();
        //session.close();
        return data;
    }

	@Override
	public Applicant getById(Long id) {
		Applicant applicant = null;
		Session session = null;
		try {
			session = openSession();
			applicant = session.get(Applicant.class, id);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return applicant;
	}

	@Override
	public void update(Applicant applicant) {
        openTransactionSession();
        Session session = getSession();
        session.update(applicant);
        closeTransactionSesstion();
	}

	@Override
	public void remove(Applicant applicant) {
        openTransactionSession();
        Session session = getSession();
        session.remove(applicant);
        closeTransactionSesstion();
	}

	public List<Company> getPrevCompanies(Applicant applicant) {
		List<Company> compList = new ArrayList<>();
		Session session = openSession();
		Applicant appl = session.get(Applicant.class, applicant.getAppl_id());
        Set<Previous_job_record> records = appl.getPrevious_job_records();
        Iterator<Previous_job_record> it = records.iterator();
        while (it.hasNext()) {
        	Company curComp = it.next().getCompany();
        	Hibernate.initialize(curComp);
        	compList.add(curComp);
        }
        session.close();
		return compList;
	}
}
