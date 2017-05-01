package ufc.quixada.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ufc.quixada.model.Caixa;
import ufc.quixada.model.Funcionario;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Integer>{

	public List<Caixa> findByDataBetweenAndAberto(Date inicio, Date fim, boolean boll);
	
	public Caixa findByFuncionarioAndAberto(Funcionario funcionario, boolean bool);
}
