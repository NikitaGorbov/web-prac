import java.sql.SQLException;

import bl.HibernateUtil;
import entity.Position;
import service.ApplicantService;
import service.CompanyService;
import service.PositionService;
import service.VacancyService;

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