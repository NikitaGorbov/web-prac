import bl.HibernateUtil;
import entity.Applicant;
import entity.Company;
import entity.Previous_job_record;
import service.ApplicantService;
import service.CompanyService;

import java.sql.SQLException;
import java.util.*;

import org.hibernate.Hibernate;

public class Domain {

    public static void main(String[] args) throws SQLException {
        CompanyService companyService = new CompanyService();
        ApplicantService applicantService = new ApplicantService();
//
//        Company company = new Company();
//        company.setComp_name("Yandex");
//        company.setDescription("Just a test description");
//        company.setLocation("Russia");

//        companyService.add(company);
        Applicant app = applicantService.getById((long) 1);
        System.out.println(app.toString());
        List comps = applicantService.getPrevCompanies(app);
        Iterator<Company> it = comps.iterator();
        while (it.hasNext()) {
        	Company test = it.next();
        	System.out.println(test.toString());
        }

        HibernateUtil.shutdown();
    }

}