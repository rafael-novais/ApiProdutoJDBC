package ultraje.domain.entity;

public class Produto {

	private int id;
    private String nome;
    private String descricao;
    private double preco;
    
	public void setNome(String nome){
        this.nome = nome;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPreco(double preco){
        this.preco = preco;
    }
    public String getNome(){
        return this.nome;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public double getPreco(){
        return this.preco;
    }
}