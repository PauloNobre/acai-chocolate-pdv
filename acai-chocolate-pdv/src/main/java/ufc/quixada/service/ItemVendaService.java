package ufc.quixada.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ufc.quixada.model.ItemVenda;
import ufc.quixada.model.Venda;
import ufc.quixada.repository.ItemVendaRepository;
import ufc.quixada.repository.ProdutoRepository;
import ufc.quixada.repository.VendaRepository;

@Service
public class ItemVendaService {
	
	@Autowired
	private ItemVendaRepository itemVendaRepository;
	
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

	public void deletar(Venda venda, int indice) {
		List<ItemVenda> itens = venda.getProdutos();
		ItemVenda item = itens.get(indice - 1);
		
		venda.removeProduto(item);
		
		vendaRepository.save(venda);
		itemVendaRepository.delete(item);
	}
}
