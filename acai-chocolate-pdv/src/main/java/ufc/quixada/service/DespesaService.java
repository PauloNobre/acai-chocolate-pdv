package ufc.quixada.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufc.quixada.model.Despesa;
import ufc.quixada.repository.DespesaRepository;

@Service
public class DespesaService {
	
	@Autowired
	private DespesaRepository despesaRepository;

	public void salvar(Despesa despesa) {
		despesa.setData(new Date());
		
		despesaRepository.save(despesa);
	}

	@SuppressWarnings("deprecation")
	public List<Despesa> buscarDiaria() {
		Date inicio = new Date();
		Date fim = new Date();
		
		inicio.setHours(00);
		inicio.setMinutes(00);
		inicio.setSeconds(00);
		
		fim.setHours(23);
		fim.setMinutes(59);
		fim.setSeconds(59);
		
		List<Despesa> despesas = despesaRepository.findByDataBetween(inicio, fim);
		
		return despesas;
	}
}
