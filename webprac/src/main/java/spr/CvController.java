package spr;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Applicant;
import entity.Cv;
import entity.Position;
import entity.Vacancy;
import service.ApplicantService;
import service.CvService;
import service.PositionService;
import service.VacancyService;

@Controller
public class CvController {
	CvService cvService = new CvService();

    @GetMapping("/cvList")
    public String cvs(Model model) {
    	List<Cv> cvs = cvService.getAll();
    	model.addAttribute("cvs", cvs);
        return "/cvList.jsp";
    }

    @GetMapping("/deleteCv")
    public String delete(@RequestParam(name = "id") long id) throws SQLException {
    	Cv cv = cvService.getById(id);
    	cvService.remove(cv);
    	return "redirect:/cvList";
    }

    @PostMapping("/cvListAdd")
    public String add(@RequestParam("appl") long applId, @RequestParam("obj") long obj, @RequestParam("exp") int exp, @RequestParam("sal") int sal) throws SQLException {
    	Cv cv = new Cv();
    	ApplicantService applService = new ApplicantService();
    	PositionService posService = new PositionService();
    	Applicant appl = applService.getById(applId);
    	cv.setApplicant(appl);
    	Position pos = posService.getById(obj);
    	cv.setObjective(pos);
    	cv.setWork_exp(exp);
    	cv.setDesired_salary(sal);
    	cvService.add(cv);
    	return ("redirect:/viewCv?id=" + cv.getCv_id());
    }
    
    @PostMapping("/filterCv")
    public String filter(@RequestParam("posFilter") long posId, Model model) throws SQLException {
    	PositionService posService = new PositionService();
    	Position pos = posService.getById(posId);
    	Set<Cv> cvs = pos.getCvs();
    	model.addAttribute("cvs", cvs);
    	return "/cvList.jsp";
    }

    @GetMapping("/viewCv")
    public String view(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	Cv cv = cvService.getById(id);
    	model.addAttribute("cv", cv);
    	return "/viewCv.jsp";
    }

    @PostMapping("/cvEdit")
    public String edit(@RequestParam(name = "cvId") long id, @RequestParam(name = "appl") long applId, @RequestParam(name = "obj") long objId, @RequestParam(name = "exp") int exp, @RequestParam(name = "sal") int sal) throws SQLException {
    	Cv cv = cvService.getById(id);
    	ApplicantService applService = new ApplicantService();
    	PositionService posService = new PositionService();
    	Applicant appl = applService.getById(applId);
    	cv.setApplicant(appl);
    	Position pos = posService.getById(objId);
    	cv.setObjective(pos);
    	cv.setWork_exp(exp);
    	cv.setDesired_salary(sal);
    	cvService.update(cv);
    	return ("redirect:/viewCv?id=" + cv.getCv_id());
    }

    @GetMapping("/relevantCvs")
    public String getRelevantCvs(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	VacancyService vacService = new VacancyService();
    	Vacancy vac = vacService.getById(id);
    	List<Cv> cvs = vacService.getRelevantCvs(vac);
    	model.addAttribute("cvs", cvs);
    	return "/cvList.jsp";
    }
}
