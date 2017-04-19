package ufc.quixada.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView formNova() {
		
		Venda venda = new Venda();
		venda = vendaService.salvarNovaVenda(venda);
		
		ModelAndView mv = new ModelAndView("/venda/venda");
		mv.addObject("venda", venda);
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView editarVenda(@PathVariable("id") Venda venda) {
		ModelAndView mv = new ModelAndView("/venda/venda");
		venda.calcularTotal();
		mv.addObject("venda", venda);
		
		return mv;
	}
	
	@PostMapping("/novo-item-venda")
	public @ResponseBody Map<String, Object> novoItemVenda(@ModelAttribute("idVenda") Integer idVenda,
			@ModelAttribute("codigo") int codigo,
			@ModelAttribute("quantidade") double quantidade) {
		
		itemVendaService.salvarNovo(idVenda, codigo, quantidade);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", "/venda/" + idVenda);
		return map;
	}
	
	@GetMapping("/carregar-tabela/{id}")
	public ModelAndView tableItens(@PathVariable("id") Venda venda) {
		ModelAndView mv = new ModelAndView("/venda/table-itens-venda :: itens-venda-list");
		
		List<ItemVenda> itens = venda.getProdutos();
		
		mv.addObject("itens", itens);
		mv.addObject("total", venda.getTotal());
		
		return mv;
	}
	
	@PostMapping("/concluir/{id}")
	public @ResponseBody Map<String, Object> concluirVenda(@PathVariable("id") Venda venda, @ModelAttribute("comanda") int comanda){
		
		vendaService.concluirVenda(venda, comanda);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", "/");
		return map;
	}
	
	@PostMapping("/finalizar/{id}")
	public @ResponseBody Map<String, Object> finalizarVenda(@PathVariable("id") Venda venda,
			@ModelAttribute("desconto") double desconto){
		
		vendaService.finalizarVenda(venda, desconto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", "/");
		return map;
	}
	
	@PostMapping("/deletar-item/{id}")
	public @ResponseBody Map<String, Object> deletarItemVenda(@PathVariable("id") Venda venda,
			@ModelAttribute("indice") int indice){
		
		itemVendaService.deletar(venda, indice);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", "/venda/" + venda.getId());
		return map;
	}
	
	@PostMapping("/cancelar/{id}")
	public @ResponseBody Map<String, Object> cancelarVenda(@PathVariable("id") Venda venda) {
		vendaService.cancelar(venda);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", "/");
		return map;
	}
}
