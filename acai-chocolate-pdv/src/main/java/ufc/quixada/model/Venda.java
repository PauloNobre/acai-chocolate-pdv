package ufc.quixada.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "VENDA_HAS_PRODUTOS", joinColumns = {
	@JoinColumn(name = "VENDA_ID", referencedColumnName = "id") }, inverseJoinColumns = {
	@JoinColumn(name = "ItemVenda_ID", referencedColumnName = "id") })
	private List<ItemVenda> produtos;

	@Enumerated(EnumType.STRING)
	private Status status;

	private int comanda;

	private double total;

	private double totalPagar;

	private double desconto;

	@ManyToOne
	@JoinColumn(name = "caixa.id")
	private Caixa caixa;

	public Venda() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ItemVenda> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ItemVenda> produtos) {
		this.produtos = produtos;
	}

	public void addProduto(ItemVenda itemVenda) {
		if (this.produtos == null) {
			this.produtos = new ArrayList<ItemVenda>();
		}
		this.produtos.add(itemVenda);
	}

	public void removeProduto(ItemVenda itemVenda) {
		this.produtos.remove(itemVenda);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getComanda() {
		return comanda;
	}

	public void setComanda(int comanda) {
		this.comanda = comanda;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(double totalPagar) {
		this.totalPagar = totalPagar;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public void calcularTotal() {
		double total = 0;
		for (ItemVenda item : this.produtos) {
			total += item.getValor();
		}
		this.total = total;
	}
}