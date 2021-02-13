package ultraje.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ultraje.DAO.ProdutoDAO;
import ultraje.model.Produto;

@Service
public class ProdutoService {

	ProdutoDAO dao;
	
	@Autowired
	public ProdutoService(ProdutoDAO dao) {
		this.dao = dao;
	}

	public List<Produto> getProdutos() throws SQLException {
		return dao.getProdutos();
	}
	
	public int adicionarProduto(Produto produto) throws SQLException {
		return dao.adicionarProduto(produto);
	}
	
	public int removerProduto(int id) throws SQLException {
		return dao.removerProduto(id);
	}
	
	public Produto alterarProduto(Produto produto, int id) throws SQLException {
		return dao.alterarProduto(produto, id);
	}
	
	public Produto getById(int id) throws SQLException {
		return dao.getById(id);
	}
	
}
