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
import entity.Education;

class EducationServiceTest {

	@Test
	void testAddGetALL() throws SQLException {
		EducationService educationService = new EducationService();
		Education education = new Education();
		Education education2 = new Education();
        education.setName("Ed1");
        education2.setName("Ed2");
        educationService.add(education);
        educationService.add(education2);
        List<Education> comps = educationService.getAll();
        assertEquals(3, comps.size());	// one education was added by BeforeEach
        assertEquals("Ed1", comps.get(1).getName());
        assertEquals("Ed2", comps.get(2).getName());
	}

	@Test
	void testAddGetByIdName() throws SQLException {
		EducationService educationService = new EducationService();
		Education education = new Education();
		Education education2 = new Education();
		Education get1, get2;
        education.setName("Ed1");
        education2.setName("Ed2");
        educationService.add(education);
        educationService.add(education2);
        get1 = educationService.getById((long) 2);
        get2 = educationService.getById((long) 3);
        assertEquals("Ed1", get1.getName());
        assertEquals("Ed2", get2.getName());
	}

	@Test
	void testUpdate() throws SQLException {
		EducationService educationService = new EducationService();
		Education education;
		List<Education> comps = educationService.getAll();
		long id = comps.get(0).getEd_id();
		education = educationService.getById(id);
		education.setName("New name");
		educationService.update(education);
        assertEquals("New name", educationService.getById(id).getName());
	}

	@Test
	void testRemove() throws SQLException {
		EducationService educationService = new EducationService();
		Education education = new Education();
        education.setName("Ed1");
        educationService.add(education);
		List<Education> eds = educationService.getAll();
		educationService.remove(eds.get(1));
		eds = educationService.getAll();

        assertEquals(1, eds.size());
        assertEquals("high school", eds.get(0).getName());
	}

	@BeforeEach
	public void before() throws SQLException {
		EducationService educationService = new EducationService();
		Education ed = new Education();
		ed.setName("high school");
        educationService.add(ed);
	}

    @AfterEach
    void setup() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM Education;").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @AfterAll
    public static void clearDataFromDatabase() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
        session.getTransaction().commit();
        session.close();
    }
}
