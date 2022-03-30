import bl.HibernateUtil;
import entity.Applicant;
import entity.Company;
import entity.Cv;
import entity.Position;
import entity.Previous_job_record;
import entity.Vacancy;
import service.ApplicantService;
import service.CompanyService;
import service.PositionService;
import service.VacancyService;

import java.sql.SQLException;
import java.util.*;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class Domain {

    public static void main(String[] args) throws SQLException {
        CompanyService companyService = new CompanyService();
        ApplicantService applicantService = new ApplicantService();
        PositionService posServ = new PositionService();
        Position pos;
        VacancyService vacService = new VacancyService();
        HibernateUtil.shutdown();
    }

}