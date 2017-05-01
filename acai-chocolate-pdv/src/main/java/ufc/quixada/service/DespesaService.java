package ufc.quixada.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufc.quixada.model.Despesa;
import ufc.quixada.model.Funcionario;
import ufc.quixada.repository.DespesaRepository;

@Service
public class DespesaService {
	
	@Autowired
	private DespesaRepository despesaRepository;

	public void salvar(Despesa despesa, Funcionario funcionario) {
		despesa.setData(new Date());
		despesa.setFuncionario(funcionario);
		
		despesaRepository.save(despesa);
	}

	@SuppressWarnings("deprecation")
	public List<Despesa> buscarDiaria(String data) {
		data = data.replace('-', '/');
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			Date inicio = new Date(formatter.parse(data).getTime());
			Date fim = new Date(formatter.parse(data).getTime());
			
			inicio.setHours(00);
			inicio.setMinutes(00);
			inicio.setSeconds(00);
			
			fim.setHours(23);
			fim.setMinutes(59);
			fim.setSeconds(59);
			
			List<Despesa> despesas = despesaRepository.findByDataBetween(inicio, fim);
			
			return despesas;
		} catch (ParseException e) {
			return null;
		}
	}
}
