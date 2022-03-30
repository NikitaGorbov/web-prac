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
import entity.Company;

class CompanyServiceTest {

	@Test
	void testAddGetAll() throws SQLException {
		CompanyService companyService = new CompanyService();
		Company company = new Company();
		Company company2 = new Company();
        company.setComp_name("comp1");
        company.setDescription("descr1");
        company.setLocation("Russia");
        company2.setComp_name("comp2");
        company2.setDescription("descr2");
        company2.setLocation("Russia");
        companyService.add(company);
        companyService.add(company2);
        List<Company> comps = companyService.getAll();
        assertEquals(3, comps.size());	// one company was added by BeforeEach
        assertEquals("comp1", comps.get(1).getComp_name());
        assertEquals("descr2", comps.get(2).getDescription());
	}

	@Test
	void testAddGetByIdName() throws SQLException {
		CompanyService companyService = new CompanyService();
		Company company = new Company();
		Company company2 = new Company();
		Company get1 = new Company();
		Company get2 = new Company();
        company.setComp_name("comp1");
        company.setDescription("descr1");
        company.setLocation("Russia");
        company2.setComp_name("comp2");
        company2.setDescription("descr2");
        company2.setLocation("Russia");
        companyService.add(company);
        companyService.add(company2);
        get1 = companyService.getById((long) 2);
        get2 = companyService.getById((long) 3);
        assertEquals("comp1", get1.getComp_name());
        assertEquals("comp2", get2.getComp_name());
	}

	@Test
	void testUpdate() throws SQLException {
		CompanyService companyService = new CompanyService();
		Company company;
		List<Company> comps = companyService.getAll();
		long id = comps.get(0).getComp_id();
		company = companyService.getById(id);
		company.setComp_name("New name");
		company.setDescription("New description");
		company.setLocation("New location");
        companyService.update(company);
        assertEquals("New name", companyService.getById(id).getComp_name());
        assertEquals("New description", companyService.getById(id).getDescription());
	}

	@Test
	void testRemove() throws SQLException {
		CompanyService companyService = new CompanyService();
		Company company = new Company();
		company.setComp_name("to delete");
		company.setDescription("to delete");
		company.setLocation("to delete");
		companyService.add(company);
		List<Company> comps = companyService.getAll();
		companyService.remove(comps.get(1));
		comps = companyService.getAll();

        assertEquals(1, comps.size());
        assertEquals("default company", comps.get(0).getComp_name());
	}

	@BeforeEach
	public void before() throws SQLException {
		CompanyService companyService = new CompanyService();
		Company comp = new Company();
        comp.setComp_name("default company");
        comp.setDescription("default description");
        comp.setLocation("default location");
        companyService.add(comp);
	}

    @AfterEach
    void setup() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM company;").executeUpdate();
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
