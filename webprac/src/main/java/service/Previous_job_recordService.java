package service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import bl.SessionUtil;
import dao.Previous_job_recordDAO;
import entity.Previous_job_record;

public class Previous_job_recordService extends SessionUtil implements Previous_job_recordDAO {


    @Override
	public void add(Previous_job_record previous_job_record) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(previous_job_record);

        //close session with a transaction
        closeTransactionSesstion();
    }

    @Override
    public List<Previous_job_record> getAll() {
    	Session session = openSession();
        Criteria criteria = session.createCriteria(Previous_job_record.class);
        List<Previous_job_record> data = criteria.list();
        session.close();
        return data;
    }

	@Override
	public Previous_job_record getById(Long id) {
		Previous_job_record previous_job_record = null;
		Session session = null;
		try {
			session = openSession();
			previous_job_record = session.get(Previous_job_record.class, id);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return previous_job_record;
	}

	@Override
	public void update(Previous_job_record previous_job_record) {
        openTransactionSession();
        Session session = getSession();
        session.update(previous_job_record);
        closeTransactionSesstion();
	}

	@Override
	public void remove(Previous_job_record previous_job_record) {
        openTransactionSession();
        Session session = getSession();
        session.remove(previous_job_record);
        closeTransactionSesstion();
	}

}
