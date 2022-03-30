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

class CvServiceTest {

	@Test
	void testAddGetALL() throws SQLException {
		PositionService positionService = new PositionService();
		ApplicantService applicantService = new ApplicantService();
		CvService cvService = new CvService();
		Cv cv1 = new Cv();
		Cv cv2 = new Cv();
		cv1.setApplicant(applicantService.getById((long) 1));
		cv1.setObjective(positionService.getById((long) 1));
		cv1.setWork_exp(5);
		cv2.setApplicant(applicantService.getById((long) 1));
		cv2.setObjective(positionService.getById((long) 1));
		cv2.setWork_exp(7);
		cvService.add(cv1);
		cvService.add(cv2);
        List<Cv> cvs = cvService.getAll();
        assertEquals(3, cvs.size());	// one cv was added by BeforeEach
        assertEquals(5, cvs.get(1).getWork_exp());
        assertEquals(7, cvs.get(2).getWork_exp());
	}

	@Test
	void testAddGetById() throws SQLException {
		PositionService positionService = new PositionService();
		ApplicantService applicantService = new ApplicantService();
		CvService cvService = new CvService();
		Cv cv1 = new Cv();
		Cv cv2 = new Cv();
		Cv get1, get2;
		cv1.setApplicant(applicantService.getById((long) 1));
		cv1.setObjective(positionService.getById((long) 1));
		cv1.setWork_exp(5);
		cv2.setApplicant(applicantService.getById((long) 1));
		cv2.setObjective(positionService.getById((long) 1));
		cv2.setWork_exp(7);
		cvService.add(cv1);
		cvService.add(cv2);
        get1 = cvService.getById(cv1.getCv_id());
        get2 = cvService.getById(cv2.getCv_id());
        assertEquals(5, get1.getWork_exp());
        assertEquals(7, get2.getWork_exp());
	}

	@Test
	void testUpdate() throws SQLException {
		CvService cvService = new CvService();
		List<Cv> cvs = cvService.getAll();
		long id = cvs.get(0).getCv_id();
		Cv cv = cvService.getById(id);
		cv.setWork_exp(10);
		cvService.update(cv);
        assertEquals(10, cvService.getById(id).getWork_exp());
	}

	@Test
	void testRemove() throws SQLException {
		PositionService positionService = new PositionService();
		ApplicantService applicantService = new ApplicantService();
		CvService cvService = new CvService();
		Cv cv1 = new Cv();
		cv1.setApplicant(applicantService.getById((long) 1));
		cv1.setObjective(positionService.getById((long) 1));
		cv1.setWork_exp(5);
		cvService.add(cv1);
		List<Cv> cvs = cvService.getAll();
		cvService.remove(cvs.get(1));
		cvs = cvService.getAll();
        assertEquals(1, cvs.size());
        assertEquals(1, cvs.get(0).getWork_exp());
	}
	
	@Test
	void testGetRelevantVacancies() throws SQLException {
		CompanyService compService = new CompanyService();
		VacancyService vacancyService = new VacancyService();
		PositionService positionService = new PositionService();
		EducationService edService = new EducationService();
		ApplicantService applService = new ApplicantService();
		Applicant appl = new Applicant();
		Education ed1 = new Education();
		Vacancy vac1 = new Vacancy();
		Vacancy vac2 = new Vacancy();
		Vacancy vac3 = new Vacancy();
		Position pos = positionService.getById((long) 1);
		Company comp = new Company();
		CvService cvService = new CvService();
		ed1.setName("edName");
		edService.add(ed1);
		appl.setAddress("addr");
		appl.setAppl_name("Name");
		appl.setEducation(ed1);
		appl.setStatus("Searching");
		applService.add(appl);
		comp.setComp_name("comp");
		comp.setDescription("descr");
		comp.setLocation("loc");
		compService.add(comp);
		vac1.setCompany(comp);
		vac1.setPosition(pos);
		vac1.setExp_required(5);
		vac1.setRequirements("None");
		vac1.setSalary(10000);
		vac2.setCompany(comp);
		vac2.setPosition(pos);
		vac2.setExp_required(3);
		vac2.setRequirements("None");
		vac2.setSalary(8000);
		vac3.setCompany(comp);
		vac3.setPosition(pos);
		vac3.setExp_required(7);
		vac3.setRequirements("None");
		vac3.setSalary(12000);
		vacancyService.add(vac1);
		vacancyService.add(vac2);
		vacancyService.add(vac3);
		Cv cv1 = new Cv();
		cv1.setApplicant(appl);
		cv1.setDesired_salary(10000);
		cv1.setObjective(pos);
		cv1.setWork_exp(5);
		cvService.add(cv1);
		List<Vacancy> vacs = cvService.getRelevantVacancies(cv1);
		assertEquals(1, vacs.size());
		assertEquals(10000, vacs.get(0).getSalary());
	}

	@BeforeAll
	public static void createApplPosition() throws SQLException {
		EducationService edService = new EducationService();
		ApplicantService applService = new ApplicantService();
		PositionService posService = new PositionService();
		Position pos = new Position();
		Education ed = new Education();
		Applicant appl = new Applicant();
		ed.setEd_id((long) 1);
		ed.setName("testEd");
		pos.setPosition_name("testPos");
		edService.add(ed);
		appl.setAppl_name("New Applicant");
		appl.setAddress("NYC");
		appl.setEducation(ed);
		appl.setStatus("Searching");
		applService.add(appl);
		posService.add(pos);
	}

	@BeforeEach
	void create() throws SQLException {
		CvService cvService = new CvService();
		ApplicantService applService = new ApplicantService();
		PositionService posService = new PositionService();
		Cv cv = new Cv();
		cv.setApplicant(applService.getById((long) 1));
		cv.setObjective(posService.getById((long) 1));
		cv.setWork_exp(1);
		cv.setDesired_salary(1000);
		cvService.add(cv);
	}

    @AfterEach
    void setup() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM Cv;").executeUpdate();
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
      	session.createSQLQuery("DELETE FROM applicant;").executeUpdate();
    	session.createSQLQuery("ALTER SEQUENCE applicant_appl_id_seq RESTART;").executeUpdate();
       	session.createSQLQuery("DELETE FROM education;").executeUpdate();
    	session.createSQLQuery("ALTER SEQUENCE education_ed_id_seq RESTART;").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}
