package ufc.quixada.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufc.quixada.exception.PdvException;
import ufc.quixada.model.Produto;
import ufc.quixada.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void salvar(Produto produto) throws PdvException{
		Produto aux = produtoRepository.findByCodigo(produto.getCodigo());
		
		if(aux != null) {
			throw new PdvException("Código já cadastrado");
		}
		
		produtoRepository.save(produto);
	}
}
