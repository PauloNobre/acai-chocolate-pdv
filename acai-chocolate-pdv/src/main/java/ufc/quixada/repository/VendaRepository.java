package ufc.quixada.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufc.quixada.model.Status;
import ufc.quixada.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {
	
	public List<Venda> findByStatus(Status status);

	public List<Venda> findByDataBetween(Date inicio, Date fim);
}
