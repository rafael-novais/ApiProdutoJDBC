package ultraje.dao;

import static ultraje.util.constants.ProdutoDAOConstants.PARAM_PRODUCT_DESCRIPTION;
import static ultraje.util.constants.ProdutoDAOConstants.PARAM_PRODUCT_ID;
import static ultraje.util.constants.ProdutoDAOConstants.PARAM_PRODUCT_NAME;
import static ultraje.util.constants.ProdutoDAOConstants.PARAM_PRODUCT_PRICE;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ultraje.domain.entity.Produto;
import ultraje.exception.DAOException;

@Repository
public class ProdutoDAO {

	@Autowired
	private NamedParameterJdbcTemplate sql;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
		
	public List<Produto> getProdutos() throws DAOException, SQLException {
		try{
			List<Produto> listaDeProdutos = sql.query("SELECT * FROM PRODUTOS", 
					(resultSet, rowNum) -> new Produto(
							resultSet.getInt("ID"), 
							resultSet.getString("NOME"),
							resultSet.getString("DESCRICAO"),
							resultSet.getDouble("PRECO")));
			
			return listaDeProdutos;
		}catch (Exception e) {
			throw new DAOException("Erro ao buscar produtos");
		}
	}
	
	public Produto adicionarProduto(Produto produto) throws DAOException, SQLException {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		Map<String, Object> parameters = new HashMap<String, Object>(); 
		parameters.put(PARAM_PRODUCT_ID, produto.getId());
		parameters.put(PARAM_PRODUCT_NAME, produto.getNome());
		parameters.put(PARAM_PRODUCT_DESCRIPTION, produto.getDescricao());
		parameters.put(PARAM_PRODUCT_PRICE, produto.getPreco());
		String sql = "INSERT INTO PRODUTOS(NOME,DESCRICAO,PRECO) VALUES(:product_name, :product_description, :product_price)";
		try{
			this.sql.update(sql, parameters);
			transactionManager.commit(status);
			return produto;
		}catch (Exception e) {
			transactionManager.rollback(status);
			throw new DAOException("Erro ao adicionar produto");
		}
	}
	
	public int removerProduto(int id) throws DAOException, SQLException {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		Map<String, Object> parameters = new HashMap<String, Object>(); 
		parameters.put(PARAM_PRODUCT_ID, id);
		try{
			this.sql.update("DELETE FROM PRODUTOS WHERE ID = :product_id", parameters);
			transactionManager.commit(status);
			return id;
		}catch (Exception e) {
			transactionManager.rollback(status);
			throw new DAOException("Erro ao remover produto");
		}
	}
	
	public Produto alterarProduto(Produto produto, int id) throws DAOException, SQLException {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		Map<String, Object> parameters = new HashMap<String, Object>(); 
		parameters.put(PARAM_PRODUCT_ID, id);
		parameters.put(PARAM_PRODUCT_NAME, produto.getNome());
		parameters.put(PARAM_PRODUCT_DESCRIPTION, produto.getDescricao());
		parameters.put(PARAM_PRODUCT_PRICE, produto.getPreco());
		String sql = "UPDATE PRODUTOS SET NOME = :product_name, DESCRICAO = :product_description, PRECO = :product_price WHERE ID = :product_id";
		try{
			this.sql.update(sql, parameters);
			transactionManager.commit(status);
			return getById(id);
		}catch (Exception e) {
			transactionManager.rollback(status);
			throw new DAOException("Erro ao alterar produto");
		}
	}
	
	public Produto getById(int id) throws DAOException, SQLException {
		Map<String, Object> parameters = new HashMap<String, Object>(); 
		parameters.put(PARAM_PRODUCT_ID, id);
		try{
			Produto produto= sql.queryForObject(
				"SELECT * FROM PRODUTOS WHERE ID = :product_id", 
				parameters,
				(resultSet, rowNum) -> new Produto(
						resultSet.getInt("ID"), 
						resultSet.getString("NOME"),
						resultSet.getString("DESCRICAO"),
						resultSet.getDouble("PRECO")));
			return produto;
		}catch (Exception e) {
			throw new DAOException("Erro ao buscar produto por ID");
		}
	}
	
}
