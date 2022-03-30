package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bl.HibernateUtil;
import entity.Position;

class PositionServiceTest {

	@Test
	void testAddGetALL() throws SQLException {
		PositionService positionService = new PositionService();
		Position position = new Position();
		Position position2 = new Position();
        position.setPosition_name("pos1");
        position2.setPosition_name("pos2");
        positionService.add(position);
        positionService.add(position2);
        List<Position> comps = positionService.getAll();
        assertEquals(3, comps.size());	// one position was added by BeforeEach
        assertEquals("pos1", comps.get(1).getPosition_name());
        assertEquals("pos2", comps.get(2).getPosition_name());
	}

	@Test
	void testAddGetByIdName() throws SQLException {
		PositionService positionService = new PositionService();
		Position position = new Position();
		Position position2 = new Position();
		Position get1, get2;
        position.setPosition_name("pos1");
        position2.setPosition_name("pos2");
        positionService.add(position);
        positionService.add(position2);
        get1 = positionService.getById((long) 2);
        get2 = positionService.getById((long) 3);
        assertEquals("pos1", get1.getPosition_name());
        assertEquals("pos2", get2.getPosition_name());
	}

	@Test
	void testUpdate() throws SQLException {
		PositionService positionService = new PositionService();
		Position position;
		List<Position> comps = positionService.getAll();
		long id = comps.get(0).getPos_id();
		position = positionService.getById(id);
		position.setPosition_name("New name");
		positionService.update(position);
        assertEquals("New name", positionService.getById(id).getPosition_name());
	}

	@Test
	void testRemove() throws SQLException {
		PositionService positionService = new PositionService();
		Position position = new Position();
        position.setPosition_name("Ed1");
        positionService.add(position);
		List<Position> eds = positionService.getAll();
		positionService.remove(eds.get(1));
		eds = positionService.getAll();

        assertEquals(1, eds.size());
        assertEquals("test position", eds.get(0).getPosition_name());
	}

	@BeforeEach
	public void before() throws SQLException {
		PositionService positionService = new PositionService();
		Position ed = new Position();
		ed.setPosition_name("test position");
        positionService.add(ed);
	}

    @AfterEach
    void setup() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM Position;").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @AfterAll
    public static void clearDataFromDatabase() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
     	session.createSQLQuery("DELETE FROM position;").executeUpdate();
       	session.createSQLQuery("ALTER SEQUENCE position_pos_id_seq RESTART;").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}