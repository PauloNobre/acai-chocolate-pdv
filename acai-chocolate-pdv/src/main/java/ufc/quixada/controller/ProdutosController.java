package ufc.quixada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.Produto;
import ufc.quixada.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutosController {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/produto/produtos-listar");
		mv.addObject("produtos", produtoRepository.findAll());
		return mv;
	}
	
	@GetMapping("/novo")
	public ModelAndView formAdicionar(Produto produto) {
		return new ModelAndView("/produto/produto-cadastrar");
	}
	
	@PostMapping("/novo")
	public ModelAndView adicionar(@ModelAttribute Produto produto) {
		produtoRepository.save(produto);
		return listar();
	}
}
