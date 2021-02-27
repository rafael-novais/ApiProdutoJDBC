package ultraje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ultraje.dao.ProdutoDAO;
import ultraje.domain.entity.Produto;
import ultraje.exception.ServiceException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoDAO dao;
	
	public List<Produto> getProdutos() throws ServiceException {
		try {
			return dao.getProdutos();			
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Produto adicionarProduto(Produto produto) throws ServiceException {
		try {
			Produto produtoAdicionado = dao.adicionarProduto(produto);
			return produtoAdicionado;
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public int removerProduto(int id) throws ServiceException {
		try {
			return dao.removerProduto(id);			
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Produto alterarProduto(Produto produto, int id) throws ServiceException {
		try {
			return dao.alterarProduto(produto, id);			
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Produto getById(int id) throws ServiceException {
		try {
			return dao.getById(id);			
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
}
