package ufc.quixada.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.Caixa;
import ufc.quixada.model.Despesa;
import ufc.quixada.model.Venda;
import ufc.quixada.service.CaixaService;
import ufc.quixada.service.DespesaService;
import ufc.quixada.service.VendaService;

@Controller
@RequestMapping("/financeiro")
public class FinanceiroController {
	
	@Autowired
	private CaixaService caixaService;
	
	@Autowired
	private DespesaService despesaService;
	
	@Autowired
	private VendaService vendaService;

	@GetMapping("/diario")
	public ModelAndView balancoDiario() {
		return new ModelAndView("/financeiro/financeiro-diario");
	}
	
	@GetMapping("/tabela-despesas/{inicio}/{fim}")
	public ModelAndView tableDespesas(@PathVariable("inicio") String inicio, 
			@PathVariable("fim") String fim) {
		ModelAndView mv = new ModelAndView("/financeiro/table-despesas :: despesas-list");
		
		List<Despesa> despesas = despesaService.buscarDiaria(inicio, fim);
		mv.addObject("despesas", despesas);
		return mv;
	}
	
	@GetMapping("/tabela-vendas/{inicio}/{fim}")
	public ModelAndView tableVendas(@PathVariable("inicio") String inicio,
			@PathVariable("fim") String fim) {
		ModelAndView mv = new ModelAndView("/financeiro/table-vendas :: vendas-list");
		
		List<Caixa> caixas = caixaService.buscarDiaria(inicio, fim);
		List<Venda> vendas = vendaService.buscarFinalizadas(caixas);
		
		mv.addObject("vendas", vendas);
		return mv;
	}
	
	@GetMapping("/gerar-grafico/{inicio}/{fim}")
	public @ResponseBody Map<String, Object> gerarGrafico(@PathVariable("inicio") String inicio,
			@PathVariable("fim") String fim) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Despesa> despesas = despesaService.buscarDiaria(inicio, fim);
		List<Caixa> caixas = caixaService.buscarDiaria(inicio, fim);
		List<Venda> vendas = vendaService.buscarFinalizadas(caixas);

		map.put("despesas", despesas);
		map.put("vendas", vendas);
		return map;
	}
}
