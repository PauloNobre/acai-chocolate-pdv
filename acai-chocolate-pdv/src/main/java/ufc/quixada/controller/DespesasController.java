package ufc.quixada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.Despesa;
import ufc.quixada.service.DespesaService;

@Controller
@RequestMapping("/despesa")
public class DespesasController {
	
	@Autowired
	private DespesaService despesaService;

	@GetMapping("/nova")
	public ModelAndView novaDespesaForm(Despesa despesa) {
		return new ModelAndView("/despesa/despesa");
	}
	
	@PostMapping("/nova")
	public ModelAndView novaDespesa(Despesa despesa) {
		despesaService.salvar(despesa);
		
		return new ModelAndView("redirect:/");
	}
}
