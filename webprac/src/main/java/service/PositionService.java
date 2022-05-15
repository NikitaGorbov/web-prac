package service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import bl.SessionUtil;
import dao.PositionDAO;
import entity.Position;

public class PositionService extends SessionUtil implements PositionDAO {


    @Override
	public void add(Position position) throws SQLException {
        //open session with a transaction
        openTransactionSession();

        Session session = getSession();
        session.save(position);

        //close session with a transaction
        closeTransactionSesstion();
    }

    @Override
    public List<Position> getAll() {
    	Session session = openSession();
        Criteria criteria = session.createCriteria(Position.class);
        List<Position> data = criteria.list();
        session.close();
        return data;
    }


	@Override
	public Position getById(Long id) {
		Position position = null;
		Session session = null;
		try {
			session = openSession();
			position = session.get(Position.class, id);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return position;
	}

	@Override
	public void update(Position position) {
        openTransactionSession();
        Session session = getSession();
        session.update(position);
        closeTransactionSesstion();
	}

	@Override
	public void remove(Position position) {
        openTransactionSession();
        Session session = getSession();
        session.remove(position);
        closeTransactionSesstion();
	}

}
