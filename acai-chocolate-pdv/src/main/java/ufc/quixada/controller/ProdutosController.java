package ufc.quixada.controller;

import java.util.HashMap;
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
	
	@GetMapping("/editar/{id}")
	public ModelAndView formEditar(@PathVariable("id") Produto produto) {
		ModelAndView mv = new ModelAndView("/produto/produto-cadastrar");
		mv.addObject("produto", produto);
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView adicionar(@ModelAttribute Produto produto) {
		produtoRepository.save(produto);
		return listar();
	}
	
	@PostMapping("/buscar")
	public @ResponseBody Map<String, Object> buscarProduto(@ModelAttribute("codigo") int codigo) {
		
		Produto produto = produtoRepository.findByCodigo(codigo);
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(produto != null) {
			map.put("produto", produto);
			return map;
		}
		
		map.put("Erro", "Produto Não Encontrado");
		return map;
	}
}
