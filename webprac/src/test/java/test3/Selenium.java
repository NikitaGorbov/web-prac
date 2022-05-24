package test3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import entity.Applicant;
import entity.Company;
import entity.Cv;
import entity.Education;
import entity.Position;
import entity.Vacancy;
import service.ApplicantService;
import service.CompanyService;
import service.CvService;
import service.EducationService;
import service.PositionService;
import service.VacancyService;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;

public class Selenium {
	static FirefoxDriver driver;
	static EducationService es = new EducationService();
    static PositionService ps = new PositionService();
    static ApplicantService as = new ApplicantService();
    static CompanyService cs = new CompanyService();
    static VacancyService vs = new VacancyService();
    static CvService cvs = new CvService();
    static Position defPos = new Position();
    static Education defEd = new Education();
    static Applicant defAppl = new Applicant();
    static Company defComp = new Company();
	
    @BeforeAll
    public static void before() throws SQLException {
		System.setProperty("webdriver.gecko.driver", "/home/nick/Downloads/geckodriver");
	    driver = new FirefoxDriver();
	    
	    defPos.setPosition_name("default position");
	    ps.add(defPos);
	    
	    defEd.setName("default education");
	    es.add(defEd);
	    
	    defAppl.setAddress("default addr");
	    defAppl.setAppl_name("default applicant");
	    defAppl.setEducation(defEd);
	    defAppl.setStatus("default status");
	    as.add(defAppl);
	    
	    defComp.setComp_name("default comp");
	    defComp.setDescription("default descr");
	    defComp.setLocation("default loc");
	    cs.add(defComp);
    }
    
    @AfterAll
    public static void cleanUp() {
    	cs.remove(defComp);
    	as.remove(defAppl);
    	ps.remove(defPos);
    	es.remove(defEd);
    	driver.quit();
    }
	
	
	@Test
	public void applicantTest() {
	    driver.get("http://127.0.0.1:8080/applicantList");

	    // add
	    int size = Integer.parseInt(driver.findElement(By.id("applListSize")).getText());
	    driver.findElement(By.name("applName")).sendKeys("name");
	    driver.findElement(By.name("applEd")).sendKeys("1");
	    driver.findElement(By.name("applStatus")).sendKeys("st");
	    driver.findElement(By.name("applAddress")).sendKeys("ad");
	    driver.findElement(By.id("addApplButton")).click();
	    String id = driver.findElement(By.name("applId")).getAttribute("value");
	    Assert.assertEquals(driver.findElement(By.name("applName")).getAttribute("value"), "name");
	    driver.get("http://127.0.0.1:8080/applicantList");
	    Assert.assertEquals(size + 1, Integer.parseInt(driver.findElement(By.id("applListSize")).getText()));
	    
	    // edit
	    driver.get("http://127.0.0.1:8080/viewAppl?id=" + id);
	    driver.findElement(By.name("applName")).clear();
	    driver.findElement(By.name("applEd")).clear();
	    driver.findElement(By.name("applStatus")).clear();
	    driver.findElement(By.name("applAddress")).clear();
	    driver.findElement(By.name("applName")).sendKeys("name2");
	    driver.findElement(By.name("applEd")).sendKeys("1");
	    driver.findElement(By.name("applStatus")).sendKeys("st2");
	    driver.findElement(By.name("applAddress")).sendKeys("ad2");
	    driver.findElement(By.id("saveButton")).click();
	    Assert.assertEquals("name2", driver.findElement(By.name("applName")).getAttribute("value"));
	    Assert.assertEquals("st2", driver.findElement(By.name("applStatus")).getAttribute("value"));
	    Assert.assertEquals("ad2", driver.findElement(By.name("applAddress")).getAttribute("value"));
	    
	    // delete
	    driver.get("http://127.0.0.1:8080/deleteAppl?id=" + id);
	    driver.get("http://127.0.0.1:8080/applicantList");
	    Assert.assertEquals(size, Integer.parseInt(driver.findElement(By.id("applListSize")).getText()));
	}
	
	@Test
	public void companyTest() {
		driver.get("http://127.0.0.1:8080/companyList");
		
		// add
		int size = Integer.parseInt(driver.findElement(By.id("compListSize")).getText());
		driver.findElement(By.name("compName")).sendKeys("name");
		driver.findElement(By.name("compDescr")).sendKeys("descr");
		driver.findElement(By.name("compLocation")).sendKeys("loc");
		driver.findElement(By.id("addCompButton")).click();
		String id = driver.findElement(By.name("compId")).getAttribute("value");
		Assert.assertEquals(driver.findElement(By.name("compName")).getAttribute("value"), "name");
		Assert.assertEquals(driver.findElement(By.name("compDescr")).getAttribute("value"), "descr");
		Assert.assertEquals(driver.findElement(By.name("compLocation")).getAttribute("value"), "loc");
		driver.get("http://127.0.0.1:8080/companyList");
		Assert.assertEquals(size + 1, Integer.parseInt(driver.findElement(By.id("compListSize")).getText()));
		
		// edit
		driver.get("http://127.0.0.1:8080/viewComp?id=" + id);
		driver.findElement(By.name("compName")).clear();
		driver.findElement(By.name("compDescr")).clear();
		driver.findElement(By.name("compLocation")).clear();
		driver.findElement(By.name("compName")).sendKeys("name2");
		driver.findElement(By.name("compDescr")).sendKeys("descr2");
		driver.findElement(By.name("compLocation")).sendKeys("loc2");
		driver.findElement(By.id("saveButton")).click();
		Assert.assertEquals(driver.findElement(By.name("compName")).getAttribute("value"), "name2");
		Assert.assertEquals(driver.findElement(By.name("compDescr")).getAttribute("value"), "descr2");
		Assert.assertEquals(driver.findElement(By.name("compLocation")).getAttribute("value"), "loc2");
		
		// delete
		driver.get("http://127.0.0.1:8080/deleteComp?id=" + id);
		driver.get("http://127.0.0.1:8080/companyList");
		Assert.assertEquals(size, Integer.parseInt(driver.findElement(By.id("compListSize")).getText()));
	}
	
	@Test
	public void educationTest() {
		driver.get("http://127.0.0.1:8080/educationList");
		
		// add
		int size = Integer.parseInt(driver.findElement(By.id("edListSize")).getText());
		driver.findElement(By.name("edName")).sendKeys("newEd");
		driver.findElement(By.id("addEdButton")).click();
		String id = driver.findElement(By.name("edId")).getAttribute("value");
		Assert.assertEquals(driver.findElement(By.name("edName")).getAttribute("value"), "newEd");
		driver.get("http://127.0.0.1:8080/educationList");
		Assert.assertEquals(size + 1, Integer.parseInt(driver.findElement(By.id("edListSize")).getText()));
		
		// edit
		driver.get("http://127.0.0.1:8080/viewEd?id=" + id);
		driver.findElement(By.name("edName")).clear();
		driver.findElement(By.name("edName")).sendKeys("newEdEdit");
		driver.findElement(By.id("saveButton")).click();
		Assert.assertEquals("newEdEdit", driver.findElement(By.name("edName")).getAttribute("value"));
		
		// delete
		driver.get("http://127.0.0.1:8080/deleteEd?id=" + id);
		driver.get("http://127.0.0.1:8080/educationList");
		Assert.assertEquals(size, Integer.parseInt(driver.findElement(By.id("edListSize")).getText()));
	}
	   
	@Test
	public void positionTest() {
		driver.get("http://127.0.0.1:8080/positionList");
		
		// add
		int size = Integer.parseInt(driver.findElement(By.id("posListSize")).getText());
		driver.findElement(By.name("posName")).sendKeys("newPos");
		driver.findElement(By.id("addPosButton")).click();
		String id = driver.findElement(By.name("posId")).getAttribute("value");
		Assert.assertEquals(driver.findElement(By.name("posName")).getAttribute("value"), "newPos");
		driver.get("http://127.0.0.1:8080/positionList");
		Assert.assertEquals(size + 1, Integer.parseInt(driver.findElement(By.id("posListSize")).getText()));
		
		// edit
		driver.get("http://127.0.0.1:8080/viewPos?id=" + id);
		driver.findElement(By.name("posName")).clear();
		driver.findElement(By.name("posName")).sendKeys("newPosEdit");
		driver.findElement(By.id("saveButton")).click();
		Assert.assertEquals("newPosEdit", driver.findElement(By.name("posName")).getAttribute("value"));
		
		// delete
		driver.get("http://127.0.0.1:8080/deletePos?id=" + id);
		driver.get("http://127.0.0.1:8080/positionList");
		Assert.assertEquals(size, Integer.parseInt(driver.findElement(By.id("posListSize")).getText()));
	}
	
	@Test
	public void cvTest() throws SQLException {
		driver.get("http://127.0.0.1:8080/cvList");
		
		// add
		int size = Integer.parseInt(driver.findElement(By.id("cvListSize")).getText());
		driver.findElement(By.name("appl")).sendKeys(defAppl.getAppl_id().toString());
		driver.findElement(By.name("obj")).sendKeys(defPos.getPos_id().toString());
		driver.findElement(By.name("exp")).sendKeys("5");
		driver.findElement(By.name("sal")).sendKeys("10000");
		driver.findElement(By.id("addCvButton")).click();
		String id = driver.findElement(By.name("cvId")).getAttribute("value");
		Assert.assertEquals(driver.findElement(By.name("appl")).getAttribute("value"), defAppl.getAppl_id().toString());
		Assert.assertEquals(driver.findElement(By.name("obj")).getAttribute("value"), defPos.getPos_id().toString());
		Assert.assertEquals(driver.findElement(By.name("exp")).getAttribute("value"), "5");
		Assert.assertEquals(driver.findElement(By.name("sal")).getAttribute("value"), "10000");
		driver.get("http://127.0.0.1:8080/cvList");
		Assert.assertEquals(size + 1, Integer.parseInt(driver.findElement(By.id("cvListSize")).getText()));
		
		// find relevant vacs
		Vacancy vac1 = new Vacancy();
		Vacancy vac2 = new Vacancy();
		vac1.setCompany(defComp);
		vac2.setCompany(defComp);
		vac1.setExp_required(6);
		vac2.setExp_required(5);
		vac1.setPosition(defPos);
		vac2.setPosition(defPos);
		vac1.setRequirements("no");
		vac2.setRequirements("no");
		vac1.setSalary(11000);
		vac2.setSalary(10000);
		vs.add(vac1);
		vs.add(vac2);
		driver.get("http://127.0.0.1:8080/relevantVacs?id=" + id);
		Assert.assertEquals(1, Integer.parseInt(driver.findElement(By.id("vacListSize")).getText()));
		vs.remove(vac2);
		vs.remove(vac1);
		
		// edit
		driver.get("http://127.0.0.1:8080/viewCv?id=" + id);
		driver.findElement(By.name("exp")).clear();
		driver.findElement(By.name("sal")).clear();
		driver.findElement(By.name("exp")).sendKeys("8");
		driver.findElement(By.name("sal")).sendKeys("15000");
		driver.findElement(By.id("saveButton")).click();
		Assert.assertEquals(driver.findElement(By.name("exp")).getAttribute("value"), "8");
		Assert.assertEquals(driver.findElement(By.name("sal")).getAttribute("value"), "15000");
		
		// delete
		driver.get("http://127.0.0.1:8080/deleteCv?id=" + id);
		driver.get("http://127.0.0.1:8080/cvList");
		Assert.assertEquals(size, Integer.parseInt(driver.findElement(By.id("cvListSize")).getText()));
	}
	
	@Test
	public void vacancyTest() throws SQLException {
		driver.get("http://127.0.0.1:8080/vacancyList");
		
		// add
		int size = Integer.parseInt(driver.findElement(By.id("vacListSize")).getText());
		driver.findElement(By.name("compId")).sendKeys(defComp.getComp_id().toString());
		driver.findElement(By.name("posId")).sendKeys(defPos.getPos_id().toString());
		driver.findElement(By.name("req")).sendKeys("java");
		driver.findElement(By.name("sal")).sendKeys("10000");
		driver.findElement(By.name("exp")).sendKeys("5");
		driver.findElement(By.id("addVacancyButton")).click();
		String id = driver.findElement(By.name("vacId")).getAttribute("value");
		Assert.assertEquals(driver.findElement(By.name("compId")).getAttribute("value"), defComp.getComp_id().toString());
		Assert.assertEquals(driver.findElement(By.name("posId")).getAttribute("value"), defPos.getPos_id().toString());
		Assert.assertEquals(driver.findElement(By.name("req")).getAttribute("value"), "java");
		Assert.assertEquals(driver.findElement(By.name("sal")).getAttribute("value"), "10000");
		Assert.assertEquals(driver.findElement(By.name("exp")).getAttribute("value"), "5");
		driver.get("http://127.0.0.1:8080/vacancyList");
		Assert.assertEquals(size + 1, Integer.parseInt(driver.findElement(By.id("vacListSize")).getText()));
		
		// find relevant CVs
		Cv cv1 = new Cv();
		Cv cv2 = new Cv();
		cv1.setApplicant(defAppl);
		cv2.setApplicant(defAppl);
		cv1.setDesired_salary(10000);
		cv2.setDesired_salary(11000);
		cv1.setObjective(defPos);
		cv2.setObjective(defPos);
		cv1.setWork_exp(5);
		cv2.setWork_exp(4);
		cvs.add(cv1);
		cvs.add(cv2);
		driver.get("http://127.0.0.1:8080/relevantCvs?id=" + id);
		Assert.assertEquals(1, Integer.parseInt(driver.findElement(By.id("cvListSize")).getText()));
		cvs.remove(cv1);
		cvs.remove(cv2);
		
		// edit
		driver.get("http://127.0.0.1:8080/viewVacancy?id=" + id);
		driver.findElement(By.name("req")).clear();
		driver.findElement(By.name("sal")).clear();
		driver.findElement(By.name("exp")).clear();
		driver.findElement(By.name("req")).sendKeys("java, C++");
		driver.findElement(By.name("sal")).sendKeys("15000");
		driver.findElement(By.name("exp")).sendKeys("6");
		driver.findElement(By.id("saveButton")).click();
		Assert.assertEquals(driver.findElement(By.name("req")).getAttribute("value"), "java, C++");
		Assert.assertEquals(driver.findElement(By.name("sal")).getAttribute("value"), "15000");
		Assert.assertEquals(driver.findElement(By.name("exp")).getAttribute("value"), "6");
		
		// delete
		driver.get("http://127.0.0.1:8080/deleteVacancy?id=" + id);
		driver.get("http://127.0.0.1:8080/vacancyList");
		Assert.assertEquals(size, Integer.parseInt(driver.findElement(By.id("vacListSize")).getText()));
	}
	
	@Test
	public void prev_job_recTest() throws InterruptedException {
		driver.get("http://127.0.0.1:8080/prevJobRecList?id=" + defAppl.getAppl_id());
		
		// add 
		int size = Integer.parseInt(driver.findElement(By.id("pjrListSize")).getText());
		driver.findElement(By.name("comp")).sendKeys(defComp.getComp_id().toString());
		driver.findElement(By.name("pos")).sendKeys(defPos.getPos_id().toString());
		driver.findElement(By.name("dur")).sendKeys("5");
		driver.findElement(By.id("addPjrButton")).click();
		Assert.assertEquals(Integer.parseInt(driver.findElement(By.id("pjrListSize")).getText()), size + 1);
		Applicant appl = as.getById(defAppl.getAppl_id());
		String id = appl.getPrevious_job_records().iterator().next().getPrev_job_record_id().toString();
		
		// delete
		driver.get("http://127.0.0.1:8080/deletePjr?id=" + id);
		Assert.assertEquals(Integer.parseInt(driver.findElement(By.id("pjrListSize")).getText()), size);
	}
}
