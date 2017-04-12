package ufc.quixada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.Despesa;
import ufc.quixada.model.Venda;
import ufc.quixada.service.DespesaService;
import ufc.quixada.service.VendaService;

@Controller
@RequestMapping("/relatorio")
public class FinanceiroController {
	
	@Autowired
	private DespesaService despesaService;
	
	@Autowired
	private VendaService vendaService;

	@GetMapping("/diario")
	public ModelAndView balancoDiario() {
		ModelAndView mv = new ModelAndView("/financeiro/balanco");
		
		List<Despesa> despesas = despesaService.buscarDiaria();
		List<Venda> vendas = vendaService.buscarDiaria();
		
		mv.addObject("despesas", despesas);
		mv.addObject("vendas", vendas);
		return mv;
	}
}
