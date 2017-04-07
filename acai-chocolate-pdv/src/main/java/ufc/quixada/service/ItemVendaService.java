package ufc.quixada.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufc.quixada.model.ItemVenda;
import ufc.quixada.model.Venda;
import ufc.quixada.repository.ProdutoRepository;
import ufc.quixada.repository.VendaRepository;

@Service
public class ItemVendaService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private VendaRepository vendaRepository;

	public ItemVenda salvaNovo(Integer idVenda, int codigo, double quantidade) {
		ItemVenda itemVenda = new ItemVenda();
		
		itemVenda.setProduto(produtoRepository.findByCodigo(codigo));
		itemVenda.setQuantidade(quantidade);
		itemVenda.setValor(itemVenda.getProduto().getValor() * quantidade);
		
		Venda venda = vendaRepository.findOne(idVenda);
		venda.addProduto(itemVenda);
		
		vendaRepository.save(venda);
		
		return itemVenda;
	}
}
