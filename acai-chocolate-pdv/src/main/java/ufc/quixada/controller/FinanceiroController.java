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

import ufc.quixada.model.Despesa;
import ufc.quixada.model.Venda;
import ufc.quixada.service.DespesaService;
import ufc.quixada.service.VendaService;

@Controller
@RequestMapping("/financeiro")
public class FinanceiroController {
	
	@Autowired
	private DespesaService despesaService;
	
	@Autowired
	private VendaService vendaService;

	@GetMapping("/diario")
	public ModelAndView balancoDiario() {
		
		return new ModelAndView("/financeiro/financeiro-diario");
	}
	
	@GetMapping("/tabela-vendas/{data}")
	public ModelAndView tableVendas(@PathVariable("data") String data) {
		ModelAndView mv = new ModelAndView("/financeiro/table-vendas :: vendas-list");
		
		List<Venda> vendas = vendaService.buscarDiaria(data);
		mv.addObject("vendas", vendas);
		return mv;
	}
	
	@GetMapping("/tabela-despesas/{data}")
	public ModelAndView tableDespesas(@PathVariable("data") String data) {
		ModelAndView mv = new ModelAndView("/financeiro/table-despesas :: despesas-list");
		
		List<Despesa> despesas = despesaService.buscarDiaria(data);
		mv.addObject("despesas", despesas);
		return mv;
	}
	
	@GetMapping("/gerar-grafico/{data}")
	public @ResponseBody Map<String, Object> gerarGrafico(@PathVariable("data") String data) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Despesa> despesas = despesaService.buscarDiaria(data);
		List<Venda> vendas = vendaService.buscarDiaria(data);

		map.put("despesas", despesas);
		map.put("vendas", vendas);
		return map;
	}
}
