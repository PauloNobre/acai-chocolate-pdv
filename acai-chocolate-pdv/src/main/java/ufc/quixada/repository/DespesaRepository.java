package ufc.quixada.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufc.quixada.model.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer>{

	public List<Despesa> findByDataBetween(Date inicio, Date fim);
}
