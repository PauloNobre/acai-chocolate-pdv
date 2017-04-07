package ufc.quixada.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.Funcionario;
import ufc.quixada.model.ItemVenda;
import ufc.quixada.model.Venda;
import ufc.quixada.service.ItemVendaService;
import ufc.quixada.service.VendaService;

@Controller
@RequestMapping("/venda")
public class VendasController {

	@Autowired
	private ItemVendaService itemVendaService;
	
	@Autowired
	private VendaService vendaService;
	
	@GetMapping("/nova")
	public ModelAndView formNova(Authentication auth) {
		
		Venda venda = new Venda();
		Funcionario funcionario = (Funcionario) auth.getPrincipal();
		venda = vendaService.salvarNovaVenda(venda, funcionario);
		
		ModelAndView mv = new ModelAndView("/venda/nova-venda");
		mv.addObject("venda", venda);
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editarVenda(@PathVariable("id") Venda venda) {
		ModelAndView mv = new ModelAndView("/venda/nova-venda");
		mv.addObject("venda", venda);
		
		return mv;
	}
	
	@PostMapping("/novo-item-venda")
	public @ResponseBody ItemVenda novoItemVenda(@ModelAttribute("idVenda") Integer idVenda,
			@ModelAttribute("codigo") int codigo,
			@ModelAttribute("quantidade") double quantidade) {
		
		ItemVenda itemVenda = itemVendaService.salvaNovo(idVenda, codigo, quantidade);
		
		return itemVenda;
	}
	
	@GetMapping("/carregar-tabela/{id}")
	public ModelAndView tableItens(@PathVariable("id") Venda venda) {
		ModelAndView mv = new ModelAndView("/venda/table-itens-venda :: itens-venda-list");
		
		List<ItemVenda> itens = venda.getProdutos();
		double total = vendaService.calcularTotal(itens);
		
		mv.addObject("itens", itens);
		mv.addObject("total", total);
		
		return mv;
	}
	
	@PostMapping("/concluir/{id}")
	public @ResponseBody Map<String, Object> concluirVenda(@PathVariable("id") Venda venda, @ModelAttribute("comanda") int comanda){
		
		vendaService.concluirVenda(venda, comanda);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", "/");
		return map;
	}
}
