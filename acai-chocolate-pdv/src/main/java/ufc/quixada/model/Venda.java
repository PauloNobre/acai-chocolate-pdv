package ufc.quixada.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@NotNull
	private double total;

	@NotNull
	private double totalPagar;

	@NotNull
	private double desconto;
	
	@NotNull
	private double custo;

	@ManyToOne
	@JoinColumn(name = "caixa.id")
	@JsonIgnore
	private Caixa caixa;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date horario;
	
	@Enumerated
	private FormaPagamento formaPagamento;

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

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public void calcularTotal() {
		double total = 0;
		for (ItemVenda item : this.produtos) {
			total += item.getValor();
		}
		this.total = total;
	}
	
	public void calcularCusto() {
		double total = 0;
		for (ItemVenda item : this.produtos) {
			total += item.getCusto();
		}
		this.custo = total;
	}
}