package ufc.quixada.service;

import java.util.Date;

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
}
