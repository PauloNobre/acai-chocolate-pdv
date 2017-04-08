package ufc.quixada.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufc.quixada.model.Funcionario;
import ufc.quixada.model.Status;
import ufc.quixada.model.Venda;
import ufc.quixada.repository.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	public Venda salvarNovaVenda(Venda venda, Funcionario funcionario) {
		
		List<Venda> vendaExistente = vendaRepository.findByStatus(Status.NOVA);
		
		if(vendaExistente != null && vendaExistente.size() != 0) {
			return vendaExistente.get(0);
		}
		
		venda.setData(new Date());
		venda.setFuncionario(funcionario);
		venda.setStatus(Status.NOVA);
		vendaRepository.save(venda);
		
		return venda;
	}

	public void concluirVenda(Venda venda, int comanda) {
		venda.setComanda(comanda);
		venda.setStatus(Status.ANDAMENTO);
		venda.calcularTotal();
		
		vendaRepository.save(venda);
	}
	
	public void finalizarVenda(Venda venda, double desconto) {
		venda.calcularTotal();
		
		venda.setDesconto(desconto);
		venda.setTotalPagar(venda.getTotal() - desconto);
		venda.setStatus(Status.FINALIZADA);
		
		vendaRepository.save(venda);
	}

	public void cancelar(Venda venda) {
		vendaRepository.delete(venda);
	}
}
