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
import entity.Education;
import service.ApplicantService;
import service.EducationService;

@Controller
public class ApplicantController {
	ApplicantService applService = new ApplicantService();
	EducationService edService = new EducationService();

    @GetMapping("/applicantList")
    public String clients(Model model) {
    	List<Applicant> applicants = applService.getAll();
    	model.addAttribute("applicants", applicants);
        return "/applicantList.jsp";
    }

    @PostMapping("/applicantListAdd")
    public String add(@RequestParam("applName") String name, @RequestParam("applEd") long edId, @RequestParam("applStatus") String status, @RequestParam("applAddress") String address) throws SQLException {
    	Applicant appl = new Applicant();
    	Education ed = edService.getById(edId);
    	appl.setAppl_name(name);
    	appl.setEducation(ed);
    	appl.setStatus(status);
    	appl.setAddress(address);
    	applService.add(appl);

    	return ("redirect:/viewAppl?id=" + appl.getAppl_id().toString());
    }

    @GetMapping("/deleteAppl")
    public String delete(@RequestParam(name = "id") long id) throws SQLException {
    	Applicant appl = applService.getById(id);
    	applService.remove(appl);
    	return "redirect:/applicantList";
    }

    @PostMapping("/applicantEdit")
    public String edit(@RequestParam(name = "applId") long id, @RequestParam(name = "applName") String name, @RequestParam("applEd") long edId, @RequestParam("applStatus") String status, @RequestParam("applAddress") String address) throws SQLException {
    	Applicant appl = applService.getById(id);
    	Education ed = edService.getById(edId);
    	appl.setAppl_name(name);
    	appl.setEducation(ed);
    	appl.setAddress(address);
    	appl.setStatus(status);
    	applService.update(appl);
    	return ("redirect:/viewAppl?id=" + appl.getAppl_id().toString());
    }

    @GetMapping("/viewAppl")
    public String view(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	Applicant appl = applService.getById(id);
    	model.addAttribute("applicant", appl);
    	return "/viewApplicant.jsp";
    }

    @GetMapping("/applCvs")
    public String viewCvList(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	Applicant appl = applService.getById(id);
    	Set<Cv> cvs = appl.getCvs();
    	model.addAttribute("cvs" ,cvs);
    	return "/cvList.jsp";
    }
}