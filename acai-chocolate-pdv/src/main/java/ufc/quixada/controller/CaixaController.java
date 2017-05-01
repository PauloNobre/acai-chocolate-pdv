package ufc.quixada.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.Caixa;
import ufc.quixada.model.Funcionario;
import ufc.quixada.repository.CaixaRepository;
import ufc.quixada.service.CaixaService;

@Controller
@RequestMapping("/caixa")
public class CaixaController {
	
	@Autowired
	private CaixaRepository caixaRepository;
	
	@Autowired
	private CaixaService caixaService;
	
	@PostMapping("/abrir")
	public @ResponseBody Map<String, Object> abrir(@RequestParam("abertura") double abertura, Authentication auth) {
		
		caixaService.salvar(abertura, (Funcionario) auth.getPrincipal());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", "/");
		return map;
	}
	
	@GetMapping("/encerrar/{id}")
	public ModelAndView formEncerrar(@PathVariable("id") Caixa caixa) {
		
		caixa = caixaService.calcularEncerramento(caixa);
		
		ModelAndView mv = new ModelAndView("/caixa/encerrar-caixa");
		mv.addObject("caixa", caixa);
		return mv;
	}
	
	@PostMapping("/encerrar/{id}")
	public ModelAndView encerrar(@PathVariable("id") Caixa caixa){
		
		caixaService.encerrar(caixa);
		
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/listar")
	public ModelAndView listar(){
		ModelAndView mv = new ModelAndView("/caixa/listar");
		
		List<Caixa> caixas = caixaRepository.findAll();
		
		mv.addObject("caixas", caixas);
		return mv;
	}
}
