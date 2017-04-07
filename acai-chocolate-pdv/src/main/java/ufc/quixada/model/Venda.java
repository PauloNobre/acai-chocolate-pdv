package ufc.quixada.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Venda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	 @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	 @JoinTable(name="VENDA_HAS_PRODUTOS", joinColumns={@JoinColumn(name="VENDA_ID", referencedColumnName="id")},
	 	inverseJoinColumns={@JoinColumn(name="ItemVenda_ID", referencedColumnName="id")})
	private List<ItemVenda> produtos;
	
	private Funcionario funcionario;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private int comanda;
	
	@Column(precision=10, scale=2)
	private double totalPagar;
	
	private double desconto;

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
		if(this.produtos == null) {
			this.produtos = new ArrayList<ItemVenda>();
		}
		this.produtos.add(itemVenda);
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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
	
}
