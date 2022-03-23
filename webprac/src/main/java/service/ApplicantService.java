package service;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.ApplicantDAO;
import entity.Applicant;
import entity.Company;
import entity.Previous_job_record;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ApplicantService extends SessionUtil implements ApplicantDAO {

	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Applicant getById(Long id) {
		Applicant applicant = null;
		Session session = null;
		try {
			session = openSession();
			applicant = (Applicant) session.get(Applicant.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return applicant;
	}

	@Override
	public void update(Applicant applicant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Applicant applicant) {
		// TODO Auto-generated method stub
		
	}
	
	public List<Company> getPrevCompanies(Applicant applicant) {
		List<Company> compList = new ArrayList<Company>();
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
