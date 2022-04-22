package StudentManagementSystem.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {


	@GetMapping(path = {"/","/students","/add-student/**","/view-student/**",})
	public String getView() {
		return "index";
	}
//	@GetMapping(path = {"/students"})
//	public String getViewStudent() {
//		return "index";
//	}
}
