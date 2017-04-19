package ufc.quixada.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufc.quixada.model.Caixa;
import ufc.quixada.repository.CaixaRepository;

@Service
public class CaixaService {

	@Autowired
	private CaixaRepository caixaRepository;
	
	public void salvar(Caixa caixa) {
		
		caixaRepository.save(caixa);
	}
	
	@SuppressWarnings("deprecation")
	public List<Caixa> buscarDiaria(String data) {
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
			
			List<Caixa> caixas = caixaRepository.findByDataBetween(inicio, fim);
			
			return caixas;
		} catch (ParseException e) {
			return null;
		}
	}
}