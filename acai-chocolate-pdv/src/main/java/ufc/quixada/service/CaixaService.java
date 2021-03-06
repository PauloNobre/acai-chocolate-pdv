package ufc.quixada.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufc.quixada.model.Caixa;
import ufc.quixada.model.Funcionario;
import ufc.quixada.model.Venda;
import ufc.quixada.repository.CaixaRepository;
import ufc.quixada.repository.FuncionarioRepository;

@Service
public class CaixaService {

	@Autowired
	private CaixaRepository caixaRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public void salvar(Caixa caixa) {
		
		caixaRepository.save(caixa);
	}
	
	@SuppressWarnings("deprecation")
	public List<Caixa> buscarDiaria(String data1, String data2) {
		data1 = data1.replace('-', '/');
		data2 = data2.replace('-', '/');
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date inicio = new Date(formatter.parse(data1).getTime());
			Date fim = new Date(formatter.parse(data2).getTime());
			
			inicio.setHours(00);
			inicio.setMinutes(00);
			inicio.setSeconds(00);
			
			fim.setHours(23);
			fim.setMinutes(59);
			fim.setSeconds(59);
			
			List<Caixa> caixas = caixaRepository.findByDataBetweenAndAberto(inicio, fim, false);
			
			return caixas;
		} catch (ParseException e) {
			return null;
		}
	}

	public void salvar(double abertura, Funcionario funcionario) {
		Caixa caixa = new Caixa();
		caixa.setAberto(true);
		caixa.setAbertura(abertura);
		caixa.setData(new Date());
		caixa.setFuncionario(funcionario);
		
		funcionario.setCaixaAberto(true);
		
		caixaRepository.save(caixa);
		funcionarioRepository.save(funcionario);
	}
	
	public void encerrar(Caixa caixa) {
		caixa = this.calcularEncerramento(caixa);
		caixa.setAberto(false);
		Funcionario funcionario = caixa.getFuncionario();
		
		funcionario.setCaixaAberto(false);
		
		caixaRepository.save(caixa);
		funcionarioRepository.save(funcionario);
	}
	
	public Caixa calcularEncerramento(Caixa caixa) {
		double valorVendas = 0;
		
		for (Venda venda : caixa.getVendas()) {
			valorVendas += venda.getTotalPagar();
		}
		
		caixa.setValorVendido(valorVendas);
		caixa.setEncerramento(caixa.getAbertura() + valorVendas);
		
		return caixa;
	}
}