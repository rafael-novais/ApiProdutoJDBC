package ultraje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ultraje.DAO.ProdutoDAO;
import ultraje.exception.ServiceException;
import ultraje.model.Produto;

@Service
public class ProdutoService {

	ProdutoDAO dao;
	
	@Autowired
	public ProdutoService(ProdutoDAO dao) {
		this.dao = dao;
	}

	public List<Produto> getProdutos() throws ServiceException {
		try {
			return dao.getProdutos();			
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public int adicionarProduto(Produto produto) throws ServiceException {
		try {
			return dao.adicionarProduto(produto);			
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
