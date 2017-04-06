package ufc.quixada.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.ItemVenda;

@Controller
@RequestMapping("/venda")
public class VendasController {

	@GetMapping("/nova")
	public ModelAndView formNova(ItemVenda itemVenda) {
		
		return new ModelAndView("/venda/nova-venda");
	}
}
