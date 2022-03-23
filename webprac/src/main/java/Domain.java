import bl.HibernateUtil;
import entity.Applicant;
import entity.Company;
import service.ApplicantService;
import service.CompanyService;

import java.sql.SQLException;
import java.util.*;

public class Domain {

    public static void main(String[] args) throws SQLException {
        CompanyService companyService = new CompanyService();


        Company company = new Company();
        company.setComp_name("Yandex");
        company.setDescription("Just a test description");
        company.setLocation("Russia");

        companyService.add(company);

        HibernateUtil.shutdown();
    }

}