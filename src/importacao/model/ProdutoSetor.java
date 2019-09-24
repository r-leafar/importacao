package importacao.model;

import java.time.LocalDate;
import java.time.LocalTime;

import importacao.annotations.Table;

@Table(name="produto_setor")
public class ProdutoSetor {
	
	private int idsetor;
	private String nome;
	private LocalDate datacadastro;
	private LocalTime horacadastro;
	
	public int getIdsetor() {
		return idsetor;
	}
	public void setIdsetor(int idsetor) {
		this.idsetor = idsetor;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDatacadastro() {
		return LocalDate.now();
	}
	public void setDatacadastro(LocalDate datacadastro) {
		this.datacadastro = datacadastro;
	}
	public LocalTime getHoracadastro() {
		return LocalTime.now();
	}
	public void setHoracadastro(LocalTime horacadastro) {
		this.horacadastro = horacadastro;
	}

}
