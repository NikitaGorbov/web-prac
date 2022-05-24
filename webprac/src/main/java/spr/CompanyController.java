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
import entity.Vacancy;
import service.CompanyService;

@Controller
public class CompanyController {
	CompanyService compService = new CompanyService();

    @GetMapping("/companyList")
    public String companies(Model model) {
    	List<Company> comps = compService.getAll();
    	model.addAttribute("companies", comps);
        return "/companyList.jsp";
    }

    @PostMapping("/companyListAdd")
    public String add(@RequestParam("compName") String name, @RequestParam("compDescr") String descr, @RequestParam("compLocation") String location) throws SQLException {
    	Company comp = new Company();
    	comp.setComp_name(name);
    	comp.setDescription(descr);
    	comp.setLocation(location);
    	compService.add(comp);
    	return "redirect:/viewComp?id=" + comp.getComp_id();
    }

    @GetMapping("/deleteComp")
    public String delete(@RequestParam(name = "id") long id) throws SQLException {
    	Company comp = compService.getById(id);
    	compService.remove(comp);
    	return "redirect:/companyList";
    }

    @GetMapping("/viewComp")
    public String view(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	Company comp = compService.getById(id);
    	model.addAttribute("company", comp);
    	return "/viewCompany.jsp";
    }

    @PostMapping("/companyEdit")
    public String edit(@RequestParam(name = "compId") long id, @RequestParam(name = "compName") String name, @RequestParam("compDescr") String descr, @RequestParam("compLocation") String location) throws SQLException {
    	Company comp = compService.getById(id);
    	comp.setComp_name(name);
    	comp.setDescription(descr);
    	comp.setLocation(location);
    	compService.update(comp);
    	return "redirect:/viewComp?id=" + comp.getComp_id();
    }

    @GetMapping("companyVacs")
    public String companyVacs(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	CompanyService compService = new CompanyService();
    	Company comp = compService.getById(id);
    	Set<Vacancy> vacs = comp.getVacancies();
    	model.addAttribute("vacancies", vacs);
    	return "/vacancyList.jsp";
    }
}
