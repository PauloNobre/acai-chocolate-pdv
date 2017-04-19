package ufc.quixada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufc.quixada.model.Caixa;
import ufc.quixada.model.Status;
import ufc.quixada.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
	
	public List<Venda> findByStatusAndCaixa(Status status, Caixa caixa);
}
