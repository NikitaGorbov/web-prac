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
import entity.Education;
import entity.Position;
import entity.Previous_job_record;

class Previous_job_recordServiceTest {

	@Test
	void testAddGetALL() throws SQLException {
		ApplicantService applService = new ApplicantService();
		CompanyService compService = new CompanyService();
		PositionService posService = new PositionService();
		Previous_job_recordService pjrService = new Previous_job_recordService();
		Previous_job_record pjr1 = new Previous_job_record();
		Previous_job_record pjr2 = new Previous_job_record();
		pjr1.setApplicant(applService.getById((long) 1));
		pjr1.setCompany(compService.getById((long) 1));
		pjr1.setPosition(posService.getById((long) 1));
		pjr1.setDuration(5);
		pjr2.setApplicant(applService.getById((long) 1));
		pjr2.setCompany(compService.getById((long) 1));
		pjr2.setPosition(posService.getById((long) 1));
		pjr2.setDuration(8);
		pjrService.add(pjr1);
		pjrService.add(pjr2);
		List<Previous_job_record> pjrs = pjrService.getAll();
		assertEquals(3, pjrs.size());
		assertEquals(5, pjrs.get(1).getDuration());
		assertEquals(8, pjrs.get(2).getDuration());
	}

	@Test
	void testAddGetById() throws SQLException {
		ApplicantService applService = new ApplicantService();
		CompanyService compService = new CompanyService();
		PositionService posService = new PositionService();
		Previous_job_recordService pjrService = new Previous_job_recordService();
		Previous_job_record pjr1 = new Previous_job_record();
		Previous_job_record pjr2 = new Previous_job_record();
		Previous_job_record get1, get2;
		pjr1.setApplicant(applService.getById((long) 1));
		pjr1.setCompany(compService.getById((long) 1));
		pjr1.setPosition(posService.getById((long) 1));
		pjr1.setDuration(5);
		pjr2.setApplicant(applService.getById((long) 1));
		pjr2.setCompany(compService.getById((long) 1));
		pjr2.setPosition(posService.getById((long) 1));
		pjr2.setDuration(8);
		pjrService.add(pjr1);
		pjrService.add(pjr2);
		get1 = pjrService.getById(pjr1.getPrev_job_record_id());
		get2 = pjrService.getById(pjr2.getPrev_job_record_id());
		assertEquals(5, get1.getDuration());
		assertEquals(8, get2.getDuration());
	}

	@Test
	void testUpdate() throws SQLException {
		Previous_job_recordService pjrService = new Previous_job_recordService();
		Previous_job_record pjr1;
		List<Previous_job_record> vacs = pjrService.getAll();
		long id = vacs.get(0).getPrev_job_record_id();
		pjr1 = pjrService.getById(id);
		pjr1.setDuration(10);
		pjrService.update(pjr1);
        assertEquals(10, pjrService.getById(id).getDuration());
	}

	@Test
	void testRemove() throws SQLException {
		ApplicantService applService = new ApplicantService();
		CompanyService compService = new CompanyService();
		PositionService posService = new PositionService();
		Previous_job_recordService pjrService = new Previous_job_recordService();
		Previous_job_record pjr1 = new Previous_job_record();
		pjr1.setApplicant(applService.getById((long) 1));
		pjr1.setCompany(compService.getById((long) 1));
		pjr1.setPosition(posService.getById((long) 1));
		pjr1.setDuration(5);
		pjrService.add(pjr1);
		List<Previous_job_record> pjrs = pjrService.getAll();
		pjrService.remove(pjrs.get(1));
		pjrs = pjrService.getAll();
        assertEquals(1, pjrs.size());
        assertEquals(1, pjrs.get(0).getDuration());
	}

	@BeforeAll
	public static void createApplPosition() throws SQLException {
		EducationService edService = new EducationService();
		ApplicantService applService = new ApplicantService();
		CompanyService compService = new CompanyService();
		PositionService posService = new PositionService();
		Position pos = new Position();
		Company comp = new Company();
		Education ed = new Education();
		Applicant appl = new Applicant();
		pos.setPosition_name("testPos");
		comp.setComp_name("test company");
		comp.setDescription("descr");
		comp.setLocation("loc");
		ed.setName("edName");
		edService.add(ed);
		posService.add(pos);
		compService.add(comp);
		appl.setAppl_name("Applicant Name");
		appl.setAddress("Address");
		appl.setEducation(ed);
		appl.setStatus("Searching");
		applService.add(appl);
	}

	@BeforeEach
	void create() throws SQLException {
		ApplicantService applService = new ApplicantService();
		CompanyService compService = new CompanyService();
		PositionService posService = new PositionService();
		Previous_job_recordService pjrService = new Previous_job_recordService();
		Previous_job_record pjr = new Previous_job_record();
		pjr.setApplicant(applService.getById((long) 1));
		pjr.setCompany(compService.getById((long) 1));
		pjr.setPosition(posService.getById((long) 1));
		pjr.setDuration(1);
		pjrService.add(pjr);
	}

    @AfterEach
    void setup() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM previous_job_record;").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @AfterAll
    public static void clearDataFromDatabase() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
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
