package spr;

import java.sql.SQLException;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Applicant;
import entity.Company;
import entity.Position;
import entity.Previous_job_record;
import service.ApplicantService;
import service.CompanyService;
import service.PositionService;
import service.Previous_job_recordService;

@Controller
public class Previous_job_recordController {
	Previous_job_recordService pjrService = new Previous_job_recordService();
	ApplicantService as = new ApplicantService();

    @GetMapping("/prevJobRecList")
    public String pjrs(Model model, @RequestParam(name = "id") long id) {
    	Applicant appl = as.getById(id);
    	Set<Previous_job_record> pjrs = appl.getPrevious_job_records();
    	model.addAttribute("pjrs", pjrs);
    	model.addAttribute("applId", id);
        return "/prevJobRecsList.jsp";
    }

    @GetMapping("/deletePjr")
    public String delete(@RequestParam(name = "id") long id) throws SQLException {
    	Previous_job_record pjr = pjrService.getById(id);
    	Applicant appl = pjr.getApplicant();
    	pjrService.remove(pjr);
    	return ("redirect:/prevJobRecList?id=" + appl.getAppl_id());
    }
    
    @PostMapping("/pjrListAdd")
    public String add(@RequestParam("appl") long applId, @RequestParam("comp") long compId, @RequestParam("pos") long posId, @RequestParam("dur") int dur) throws SQLException {
    	CompanyService cs = new CompanyService();
    	PositionService ps = new PositionService();
    	Applicant appl = as.getById(applId);
    	Company comp = cs.getById(compId);
    	Position pos = ps.getById(posId);
    	Previous_job_record pjr = new Previous_job_record();
    	pjr.setApplicant(appl);
    	pjr.setCompany(comp);
    	pjr.setPosition(pos);
    	pjr.setDuration(dur);
    	pjrService.add(pjr);
    	return ("redirect:/prevJobRecList?id=" + appl.getAppl_id());
    }
}
