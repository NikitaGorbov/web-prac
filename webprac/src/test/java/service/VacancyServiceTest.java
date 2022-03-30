package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bl.HibernateUtil;
import entity.Applicant;
import entity.Company;
import entity.Cv;
import entity.Education;
import entity.Position;
import entity.Vacancy;

class VacancyServiceTest {

	@Test
	void testAddGetALL() throws SQLException {
		PositionService positionService = new PositionService();
		CompanyService companyService = new CompanyService();
		VacancyService vacancyService = new VacancyService();
		Vacancy vacancy1 = new Vacancy();
		Vacancy vacancy2 = new Vacancy();
		vacancy1.setCompany(companyService.getById((long) 1));
		vacancy1.setPosition(positionService.getById((long) 1));
		vacancy1.setRequirements("Java");
		vacancy2.setCompany(companyService.getById((long) 1));
		vacancy2.setPosition(positionService.getById((long) 1));
		vacancy2.setRequirements("C++");
		vacancyService.add(vacancy1);
		vacancyService.add(vacancy2);
        List<Vacancy> vacs = vacancyService.getAll();
        assertEquals(3, vacs.size());	// one vacancy was added by BeforeEach
        assertEquals("Java", vacs.get(1).getRequirements());
        assertEquals("C++", vacs.get(2).getRequirements());
	}

	@Test
	void testAddGetById() throws SQLException {
		PositionService positionService = new PositionService();
		CompanyService companyService = new CompanyService();
		VacancyService vacancyService = new VacancyService();
		Vacancy vacancy1 = new Vacancy();
		Vacancy vacancy2 = new Vacancy();
		Vacancy get1, get2;
		vacancy1.setCompany(companyService.getById((long) 1));
		vacancy1.setPosition(positionService.getById((long) 1));
		vacancy1.setRequirements("Java");
		vacancy2.setCompany(companyService.getById((long) 1));
		vacancy2.setPosition(positionService.getById((long) 1));
		vacancy2.setRequirements("C++");
		vacancyService.add(vacancy1);
		vacancyService.add(vacancy2);
        get1 = vacancyService.getById(vacancy1.getVac_id());
        get2 = vacancyService.getById(vacancy2.getVac_id());
        assertEquals("Java", get1.getRequirements());
        assertEquals("C++", get2.getRequirements());
	}

	@Test
	void testUpdate() throws SQLException {
		VacancyService vacancyService = new VacancyService();
		Vacancy vacancy1;
		List<Vacancy> vacs = vacancyService.getAll();
		long id = vacs.get(0).getVac_id();
		vacancy1 = vacancyService.getById(id);
		vacancy1.setRequirements("Golang");
		vacancyService.update(vacancy1);
        assertEquals("Golang", vacancyService.getById(id).getRequirements());
	}

	@Test
	void testRemove() throws SQLException {
		PositionService positionService = new PositionService();
		CompanyService companyService = new CompanyService();
		VacancyService vacancyService = new VacancyService();
		Vacancy vacancy1 = new Vacancy();
		vacancy1.setCompany(companyService.getById((long) 1));
		vacancy1.setPosition(positionService.getById((long) 1));
		vacancy1.setRequirements("Java");
		vacancyService.add(vacancy1);
		List<Vacancy> vacs = vacancyService.getAll();
		vacancyService.remove(vacs.get(1));
		vacs = vacancyService.getAll();
        assertEquals(1, vacs.size());
        assertEquals("None", vacs.get(0).getRequirements());
	}
	
	@Test
	void testGetRelevantCvs() throws SQLException {
		CompanyService compService = new CompanyService();
		VacancyService vacancyService = new VacancyService();
		PositionService positionService = new PositionService();
		EducationService edService = new EducationService();
		ApplicantService applService = new ApplicantService();
		Applicant appl = new Applicant();
		Education ed1 = new Education();
		Vacancy vac = new Vacancy();
		Position pos = positionService.getById((long) 1);
		Company comp = compService.getById((long) 1);
		CvService cvService = new CvService();
		ed1.setName("edName");
		edService.add(ed1);
		appl.setAddress("addr");
		appl.setAppl_name("Name");
		appl.setEducation(ed1);
		appl.setStatus("Searching");
		applService.add(appl);
		vac.setCompany(comp);
		vac.setPosition(pos);
		vac.setExp_required(5);
		vac.setRequirements("None");
		vac.setSalary(10000);
		vacancyService.add(vac);
		Cv cv1 = new Cv();
		Cv cv2 = new Cv();
		Cv cv3 = new Cv();
		cv1.setApplicant(appl);
		cv1.setDesired_salary(8000);
		cv1.setObjective(pos);
		cv1.setWork_exp(3);
		cv2.setApplicant(appl);
		cv2.setDesired_salary(10000);
		cv2.setObjective(pos);
		cv2.setWork_exp(5);
		cv3.setApplicant(appl);
		cv3.setDesired_salary(12000);
		cv3.setObjective(pos);
		cv3.setWork_exp(7);
		cvService.add(cv1);
		cvService.add(cv2);
		cvService.add(cv3);
		List<Cv> cvs = vacancyService.getRelevantCvs(vac);
		assertEquals(1, cvs.size());
		assertEquals(10000, cvs.get(0).getDesired_salary());
	}

	@BeforeAll
	public static void createApplPosition() throws SQLException {
		CompanyService compService = new CompanyService();
		PositionService posService = new PositionService();
		Position pos = new Position();
		Company comp = new Company();
		pos.setPosition_name("testPos");
		comp.setComp_name("test company");
		comp.setDescription("descr");
		comp.setLocation("loc");
		posService.add(pos);
		compService.add(comp);
	}

	@BeforeEach
	void create() throws SQLException {
		VacancyService vacancyService = new VacancyService();
		PositionService posService = new PositionService();
		CompanyService compService = new CompanyService();
		Vacancy vac = new Vacancy();
		vac.setCompany(compService.getById((long) 1));
		vac.setPosition(posService.getById((long) 1));
		vac.setRequirements("None");
		vacancyService.add(vac);
	}

    @AfterEach
    void setup() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM vacancy;").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @AfterAll
    public static void clearDataFromDatabase() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	session.createSQLQuery("DELETE FROM cv;").executeUpdate();
    	session.createSQLQuery("ALTER SEQUENCE cv_cv_id_seq RESTART;").executeUpdate();
    	session.createSQLQuery("DELETE FROM vacancy;").executeUpdate();
    	session.createSQLQuery("ALTER SEQUENCE vacancy_vac_id_seq RESTART;").executeUpdate();
    	session.createSQLQuery("DELETE FROM company;").executeUpdate();
    	session.createSQLQuery("ALTER SEQUENCE company_comp_id_seq RESTART;").executeUpdate();
    	session.createSQLQuery("DELETE FROM position;").executeUpdate();
    	session.createSQLQuery("ALTER SEQUENCE position_pos_id_seq RESTART;").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}