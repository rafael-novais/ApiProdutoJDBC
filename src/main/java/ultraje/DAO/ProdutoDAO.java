package ultraje.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ultraje.BDConfig.ConnectionFactory;
import ultraje.exception.DAOException;
import ultraje.model.Produto;

@Repository
public class ProdutoDAO {
	
	ConnectionFactory connectionFactory;
	
	@Autowired
	public ProdutoDAO(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory; 
	}
	
	public List<Produto> getProdutos() throws DAOException, SQLException {
		Connection connection = connectionFactory.openConnection();
		ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();
		String sql = "SELECT * FROM PRODUTOS";
		
		try(PreparedStatement stm = 
        		connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stm.execute();
			ResultSet resultSet = stm.getResultSet();
			while(resultSet.next()){
	            Produto produto = new Produto();
	            produto.setId(resultSet.getInt("ID"));
	            produto.setNome(resultSet.getString("NOME"));
	            produto.setDescricao(resultSet.getString("DESCRICAO"));
	            produto.setPreco(resultSet.getDouble("PRECO"));

	            listaDeProdutos.add(produto);
	        }
			return listaDeProdutos;
		}catch (Exception e) {
			connection.rollback();
			throw new DAOException("Erro ao buscar produtos");
		}finally {
			connection.close();
		}
	}
	
	public int adicionarProduto(Produto produto) throws DAOException, SQLException {
		Connection connection = connectionFactory.openConnection();
        String sql = "INSERT INTO PRODUTOS(NOME, DESCRICAO, PRECO) VALUES(?, ?, ?)";
        
        try (PreparedStatement stm = 
        		connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
        	
	        	stm.setString(1, produto.getNome());
	        	stm.setString(2, produto.getDescricao());
	        	stm.setDouble(3, produto.getPreco());
	        	stm.execute();
	        	ResultSet resultSet = stm.getGeneratedKeys();
	        	
	        	int idGerado = 0;

	        	while(resultSet.next()) {
	        		idGerado = resultSet.getInt(1);
	        	}
	        	
	        	connection.commit();
	        	return idGerado;
        	
        }catch(Exception e) {
        	connection.rollback();        	
        	throw new DAOException("Erro ao adicionar produto");
        }finally {
        	connection.close();  
        }
	}
	
	public int removerProduto(int id) throws DAOException, SQLException {
		Connection connection = connectionFactory.openConnection();
		String sql = "DELETE FROM PRODUTOS WHERE ID=?";
		try(PreparedStatement stm = 
        		connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, id);
			stm.execute();
			connection.commit();
			return id;
		}catch (Exception e) {
			connection.rollback();
			throw new DAOException("Erro ao remover produto");
		}finally {
			connection.close();
		}
	}
	
	public Produto alterarProduto(Produto produto, int id) throws DAOException, SQLException {
		Connection connection = connectionFactory.openConnection();
		String sql = "UPDATE PRODUTOS SET NOME = ?, DESCRICAO = ?, PRECO = ? WHERE ID = ?";
		
		try(PreparedStatement stm = 
        		connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stm.setString(1, produto.getNome());
			stm.setString(2, produto.getDescricao());
			stm.setDouble(3, produto.getPreco());
			stm.setInt(4, id);
			stm.execute();
			connection.commit();
			return getById(id);
		}catch (Exception e) {
			connection.rollback();
			throw new DAOException("Erro ao alterar produto");
		}finally {
			connection.close();
		}
	}
	
	public Produto getById(int id) throws DAOException, SQLException {
		Connection connection = connectionFactory.openConnection();
		String sql = "SELECT * FROM PRODUTOS WHERE ID = ?";
		Produto produto = new Produto();
		try(PreparedStatement stm = 
        		connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stm.setInt(1, id);
			stm.execute();
			connection.commit();
			ResultSet resultSet = stm.getResultSet();
			while(resultSet.next()){
	            produto.setId(resultSet.getInt("ID"));
	            produto.setNome(resultSet.getString("NOME"));
	            produto.setDescricao(resultSet.getString("DESCRICAO"));
	            produto.setPreco(resultSet.getDouble("PRECO"));
	        }
			return produto;
		}catch (Exception e) {
			connection.rollback();
			throw new DAOException("Erro ao buscar produto por ID");
		}finally {
			connection.close();
		}
	}
	
}
