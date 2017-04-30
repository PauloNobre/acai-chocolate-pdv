package ufc.quixada.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Caixa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="caixa.id")
	private Funcionario funcionario;
	
	private double abertura;
	
	private double fechamento;
	
	private double valorVendido;
	
	private boolean aberto;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@OneToMany(mappedBy= "caixa", targetEntity = Venda.class, cascade = CascadeType.ALL)
	private List<Venda> vendas;

	public Caixa() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public double getAbertura() {
		return abertura;
	}

	public void setAbertura(double abertura) {
		this.abertura = abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public void setFechamento(double fechamento) {
		this.fechamento = fechamento;
	}

	public double getValorVendido() {
		return valorVendido;
	}

	public void setValorVendido(double valorVendido) {
		this.valorVendido = valorVendido;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
}