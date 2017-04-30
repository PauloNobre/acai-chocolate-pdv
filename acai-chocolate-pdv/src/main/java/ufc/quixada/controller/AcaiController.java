package ufc.quixada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.Caixa;
import ufc.quixada.model.Funcionario;
import ufc.quixada.model.Status;
import ufc.quixada.model.Venda;
import ufc.quixada.repository.CaixaRepository;
import ufc.quixada.repository.VendaRepository;

@Controller
public class AcaiController {
	
	@Autowired
	private CaixaRepository caixaRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("/login");
	}
	
	@GetMapping("/")
	public ModelAndView home(Authentication auth) {
		ModelAndView mv = new ModelAndView("/home");
		
		Funcionario funcionario = (Funcionario) auth.getPrincipal();
		if(funcionario.isCaixaAberto()){
			Caixa caixaAberto = caixaRepository.findByFuncionarioAndAberto(funcionario, true);
			List<Venda> vendas = vendaRepository.findByStatus(Status.ANDAMENTO);
			mv.addObject("vendas", vendas);
			mv.addObject("caixa", caixaAberto);
		}
		
		return mv;
	}
}
