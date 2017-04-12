package ufc.quixada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ufc.quixada.model.Funcionario;
import ufc.quixada.model.Papel;
import ufc.quixada.repository.FuncionarioRepository;
import ufc.quixada.service.FuncionarioService;

@Controller
@RequestMapping("/funcionario")
public class FuncionariosController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping("/novo")
	public ModelAndView formNovoFuncionario(Funcionario funcionario) {
		ModelAndView mv =  new ModelAndView("/funcionario/funcionario-cadastrar");
		mv.addObject("funcionario", funcionario);
		mv.addObject("papeis", Papel.values());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView novoFuncionario(Funcionario funcionario) {
		funcionarioService.salvar(funcionario);
		return listar();
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editarFuncionario(@PathVariable("id") Funcionario funcionario) {
		return formNovoFuncionario(funcionario);
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("/funcionario/funcionarios-listar");
		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		mv.addObject("funcionarios", funcionarios);
		return mv;
	}
}
