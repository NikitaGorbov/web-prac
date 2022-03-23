package service;

import bl.HibernateUtil;
import bl.SessionUtil;
import dao.PositionDAO;
import entity.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PositionService extends SessionUtil implements PositionDAO {

	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Position position) {
		// TODO Auto-generated method stub
		
	}

}
