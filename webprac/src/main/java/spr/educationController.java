package spr;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Education;
import service.EducationService;

@Controller
public class educationController {
	EducationService edService = new EducationService();

    @GetMapping("/educationList")
    public String educations(Model model) {
    	List<Education> educations = edService.getAll();
    	model.addAttribute("educations", educations);
        return "/educationList.jsp";
    }

    @GetMapping("/deleteEd")
    public String delete(@RequestParam(name = "id") long id) throws SQLException {
    	Education ed = edService.getById(id);
    	edService.remove(ed);
    	return "redirect:/educationList";
    }
    @PostMapping("/edListAdd")
    public String add(@RequestParam("edName") String name) throws SQLException {
    	Education ed = new Education();
    	ed.setName(name);
    	edService.add(ed);
    	return ("redirect:/viewEd?id=" + ed.getEd_id());
    }

    @GetMapping("/viewEd")
    public String view(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	Education ed = edService.getById(id);
    	model.addAttribute("education", ed);
    	return "/viewEducation.jsp";
    }

    @PostMapping("/educationEdit")
    public String edit(@RequestParam(name = "edId") long id, @RequestParam(name = "edName") String name) throws SQLException {
    	Education ed = edService.getById(id);
    	ed.setName(name);
    	edService.update(ed);
    	return ("redirect:/viewEd?id=" + ed.getEd_id());
    }
}
