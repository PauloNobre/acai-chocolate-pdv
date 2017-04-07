package ufc.quixada.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ufc.quixada.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	public Produto findByCodigo(int codigo);
}
