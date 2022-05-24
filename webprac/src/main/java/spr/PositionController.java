package spr;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entity.Position;
import service.PositionService;

@Controller
public class PositionController {
	PositionService posService = new PositionService();

    @GetMapping("/positionList")
    public String companies(Model model) {
    	List<Position> positions = posService.getAll();
    	model.addAttribute("positions", positions);
        return "/positionList.jsp";
    }

    @GetMapping("/deletePos")
    public String delete(@RequestParam(name = "id") long id) throws SQLException {
    	Position pos = posService.getById(id);
    	posService.remove(pos);
    	return "redirect:/positionList";
    }

    @PostMapping("/posListAdd")
    public String add(@RequestParam("posName") String name) throws SQLException {
    	Position pos = new Position();
    	pos.setPosition_name(name);
    	posService.add(pos);
    	return ("redirect:/viewPos?id=" + pos.getPos_id());
    }

    @GetMapping("/viewPos")
    public String view(@RequestParam(name = "id") long id, Model model) throws SQLException {
    	Position pos = posService.getById(id);
    	model.addAttribute("position", pos);
    	return "/viewPosition.jsp";
    }

    @PostMapping("/positionEdit")
    public String edit(@RequestParam(name = "posId") long id, @RequestParam(name = "posName") String name) throws SQLException {
    	Position pos = posService.getById(id);
    	pos.setPosition_name(name);
    	posService.update(pos);
    	return ("redirect:/viewPos?id=" + pos.getPos_id());
    }
}
