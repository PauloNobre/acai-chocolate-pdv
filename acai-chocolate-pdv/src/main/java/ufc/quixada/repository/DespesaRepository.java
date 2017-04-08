package ufc.quixada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufc.quixada.model.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer>{

}
