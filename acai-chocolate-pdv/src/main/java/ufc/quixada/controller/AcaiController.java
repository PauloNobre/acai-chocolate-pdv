package ufc.quixada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.Status;
import ufc.quixada.model.Venda;
import ufc.quixada.repository.VendaRepository;

@Controller
public class AcaiController {
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("/login");
	}
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("/home");
		List<Venda> vendas = vendaRepository.findByStatus(Status.ANDAMENTO);
		mv.addObject("vendas", vendas);
		return mv;
	}
}
