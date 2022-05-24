package spr;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Company;
import entity.Cv;
import entity.Position;
import entity.Vacancy;
import service.CompanyService;
import service.CvService;
import service.PositionService;
import service.VacancyService;

@Controller
public class VacancyController {
	VacancyService vacService = new VacancyService();

    @GetMapping("/vacancyList")
    public String vacancies(Model model) {
    	List<Vacancy> vacancies = vacService.getAll();
    	model.addAttribute("vacancies", vacancies);
        return "/vacancyList.jsp";
    }

    @GetMapping("/deleteVacancy")
    public String delete(@RequestParam(name = "id") long id) throws SQLException {
    	Vacancy vac = vacService.getById(id);
    	vacService.remove(vac);
    	return "redirect:/vacancyList";
    }

    @PostMapping("/vacListAdd")
    public String add(@RequestParam("compId") long compId, @RequestParam("posId") long posId, @RequestParam("req") String req, @RequestParam("sal") int sal, @RequestParam("exp") int exp) throws SQLException {
    	Vacancy vac = new Vacancy();
    	CompanyService compServ = new CompanyService();
    	PositionService posService = new PositionService();
    	Company comp = compServ.getById(compId);
    	vac.setCompany(comp);
    	Position pos = posService.getById(posId);
    	vac.setPosition(pos);
    	vac.setExp_required(exp);
    	vac.setRequirements(req);
    	vac.setSalary(sal);
    	vacService.add(vac);
    	return ("redirect:/viewVacancy?id=" + vac.getVac_id());
    }
    
    @PostMapping("/filterVacancy")
    public String filter(@RequestParam("posFilter") long posId, Model model) throws SQLException {
    	PositionService posService = new PositionService();
    	Position pos = posService.getById(posId);
    	Set<Vacancy> vacs = pos.getVacancies();
    	model.addAttribute("vacancies", vacs);
    	return "/vacancyList.jsp";
    }

    @GetMapping("/viewVacancy")
    public String view(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	Vacancy vac = vacService.getById(id);
    	model.addAttribute("vacancy", vac);
    	return "/viewVacancy.jsp";
    }

    @PostMapping("/vacEdit")
    public String edit(@RequestParam("vacId") long vacId, @RequestParam("compId") long compId, @RequestParam("posId") long posId, @RequestParam("req") String req, @RequestParam("sal") int sal, @RequestParam("exp") int exp) throws SQLException {
    	Vacancy vac = vacService.getById(vacId);
    	CompanyService compServ = new CompanyService();
    	PositionService posService = new PositionService();
    	Company comp = compServ.getById(compId);
    	vac.setCompany(comp);
    	Position pos = posService.getById(posId);
    	vac.setPosition(pos);
    	vac.setExp_required(exp);
    	vac.setRequirements(req);
    	vac.setSalary(sal);
    	vacService.update(vac);
    	return ("redirect:/viewVacancy?id=" + vac.getVac_id());
    }

    @GetMapping("/relevantVacs")
    public String viewRelevant(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	CvService cvService = new CvService();
    	Cv cv = cvService.getById(id);
    	List<Vacancy> relevantVacs = cvService.getRelevantVacancies(cv);
    	model.addAttribute("vacancies", relevantVacs);
    	return "/vacancyList.jsp";
    }
}
