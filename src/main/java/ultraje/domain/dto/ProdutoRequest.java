package ultraje.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProdutoRequest {
	@NotNull @NotEmpty
    private String nome;
    private String descricao;
    private double preco;
    
	public ProdutoRequest(String nome, String descricao, double preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
}
