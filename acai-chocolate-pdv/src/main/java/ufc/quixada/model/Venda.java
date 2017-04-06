package ufc.quixada.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;
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

	public Venda() {
		super();
	}

	public Venda(Integer id, List<ItemVenda> produtos, Funcionario funcionario, Date data) {
		super();
		this.id = id;
		this.produtos = produtos;
		this.funcionario = funcionario;
		this.data = data;
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
}
