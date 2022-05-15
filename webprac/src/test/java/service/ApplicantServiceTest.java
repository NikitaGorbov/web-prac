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

class ApplicantServiceTest {

	@Test
	void testAddGetAll() throws SQLException {
		EducationService edService = new EducationService();
		Education ed = edService.getById((long) 1);
		ApplicantService applicantService = new ApplicantService();
		Applicant appl = new Applicant();
		Applicant appl2 = new Applicant();
		appl.setAppl_name("TestName");
		appl.setAddress("NYC");
		appl.setEducation(ed);
		appl.setStatus("Searching");
		appl2.setAppl_name("TestName2");
		appl2.setAddress("LA");
		appl2.setEducation(ed);
		appl2.setStatus("Searching");
        applicantService.add(appl);
        applicantService.add(appl2);
        List<Applicant> appls = applicantService.getAll();
        assertEquals(3, appls.size());	// one applicant was added by BeforeEach
        assertEquals("TestName", appls.get(1).getAppl_name());
        assertEquals("LA", appls.get(2).getAddress());
	}

	@Test
	void testAddGetByIdName() throws SQLException {
		EducationService edService = new EducationService();
		Education ed = edService.getById((long) 1);
		ApplicantService applicantService = new ApplicantService();
		Applicant appl = new Applicant();
		Applicant appl2 = new Applicant();
		Applicant get1, get2;
		appl.setAppl_name("TestName");
		appl.setAddress("NYC");
		appl.setEducation(ed);
		appl.setStatus("Searching");
		appl2.setAppl_name("TestName2");
		appl2.setAddress("LA");
		appl2.setEducation(ed);
		appl2.setStatus("Searching");
        applicantService.add(appl);
        applicantService.add(appl2);
        get1 = applicantService.getById((long) 2);
        get2 = applicantService.getById((long) 3);
        assertEquals("TestName", get1.getAppl_name());
        assertEquals("TestName2", get2.getAppl_name());
	}

	@Test
	void testUpdate() throws SQLException {
		ApplicantService applicantService = new ApplicantService();
		Applicant applicant;
		List<Applicant> appls = applicantService.getAll();
		long id = appls.get(0).getAppl_id();
		applicant = applicantService.getById(id);
		applicant.setAppl_name("New name");
		applicant.setStatus("Busy");
		applicantService.update(applicant);
        assertEquals("New name", applicantService.getById(id).getAppl_name());
        assertEquals("Busy", applicantService.getById(id).getStatus());
		assertEquals(1,1);
	}

	@Test
	void testRemove() throws SQLException {
		EducationService educationService = new EducationService();
		Education ed = educationService.getById((long) 1);
		ApplicantService applicantService = new ApplicantService();
		Applicant appl = new Applicant();
		appl.setAppl_name("New Applicant");
		appl.setAddress("NYC");
		appl.setEducation(ed);
		appl.setStatus("Searching");
        applicantService.add(appl);
        List<Applicant> appls = applicantService.getAll();
        applicantService.remove(appls.get(1));
        appls = applicantService.getAll();
      assertEquals(1, appls.size());
      assertEquals("John Smith", appls.get(0).getAppl_name());
	}

	@Test
	void testGetPrevCompanies() throws SQLException {
		CompanyService compService = new CompanyService();
		EducationService edService = new EducationService();
		PositionService posService = new PositionService();
		Previous_job_recordService pjrService = new Previous_job_recordService();
		ApplicantService applService = new ApplicantService();
		Education ed1 = new Education();
		Company comp1 = new Company();
		Company comp2 = new Company();
		Position pos1 = new Position();
		Applicant appl1 = new Applicant();
		Previous_job_record pjr1 = new Previous_job_record();
		Previous_job_record pjr2 = new Previous_job_record();
		ed1.setName("ed");
		edService.add(ed1);
		comp1.setComp_name("C1");
		comp2.setComp_name("C2");
		comp1.setDescription("descr1");
		comp2.setDescription("descr2");
		comp1.setLocation("Loc1");
		comp2.setLocation("Loc2");
		compService.add(comp1);
		compService.add(comp2);
		appl1.setAppl_name("Nick");
		appl1.setEducation(ed1);
		appl1.setStatus("Searching");
		appl1.setAddress("addr");
		applService.add(appl1);
		pos1.setPosition_name("pos1");
		posService.add(pos1);
		pjr1.setApplicant(appl1);
		pjr1.setCompany(comp1);
		pjr1.setDuration(5);
		pjr1.setPosition(pos1);
		pjr2.setApplicant(appl1);
		pjr2.setCompany(comp2);
		pjr2.setDuration(10);
		pjr2.setPosition(pos1);
		pjrService.add(pjr1);
		pjrService.add(pjr2);
		List<Company> comps = applService.getPrevCompanies(appl1);
		assertEquals(2, comps.size());
	}

	@BeforeAll
	public static void createEd() throws SQLException {
		EducationService educationService = new EducationService();
		Education ed = new Education();
		ed.setEd_id((long) 1);
		ed.setName("ed");
		educationService.add(ed);
	}

	@BeforeEach
	public void before() throws SQLException {
		EducationService educationService = new EducationService();
		Education ed = educationService.getById((long) 1);
		ApplicantService applicantService = new ApplicantService();
		Applicant appl = new Applicant();
		appl.setAppl_name("John Smith");
		appl.setAddress("NYC");
		appl.setEducation(ed);
		appl.setStatus("Searching");
        applicantService.add(appl);
	}

    @AfterEach
    void setup() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM previous_job_record;").executeUpdate();
            session.createSQLQuery("DELETE FROM applicant;").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @AfterAll
    public static void clearDataFromDatabase() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
//    	session.createSQLQuery("TRUNCATE TABLE applicant");
//    	session.createSQLQuery("ALTER SEQUENCE appl_id_seq RESTART");
        session.getTransaction().commit();
        session.close();
    }


}
