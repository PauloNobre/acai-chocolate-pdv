package ufc.quixada.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FuncionarioController {

	@GetMapping("/")
	public ModelAndView listarTodos() {
		return new ModelAndView("/test");
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("/login");
	}
}
