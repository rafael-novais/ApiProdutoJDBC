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
import ultraje.model.Produto;

@Repository
public class ProdutoDAO {
	
	ConnectionFactory connectionFactory;
	
	@Autowired
	public ProdutoDAO(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory; 
	}
	
	public List<Produto> getProdutos() throws SQLException {
		Connection connection = connectionFactory.openConnection();
		ArrayList<Produto> listaDeProdutos = new ArrayList<Produto>();
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM PRODUTOS");
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()){
            Produto produto = new Produto();
            produto.setId(resultSet.getInt("ID"));
            produto.setNome(resultSet.getString("NOME"));
            produto.setDescricao(resultSet.getString("DESCRICAO"));
            produto.setPreco(resultSet.getDouble("PRECO"));

            listaDeProdutos.add(produto);
        }
        connection.close();
		return listaDeProdutos;
	}
	
	public int adicionarProduto(Produto produto) throws SQLException {
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
        	throw new SQLException("Erro ao adicionar produto");
        }finally {
        	connection.close();  
        }
	}
	
	public int removerProduto(int id) throws SQLException {
		Connection connection = connectionFactory.openConnection();
        Statement statement = connection.createStatement();
        statement.execute("DELETE FROM PRODUTOS WHERE ID=" + id);
        connection.commit();
        connection.close();
		return id;
	}
	
	public Produto alterarProduto(Produto produto, int id) throws SQLException {
		Connection connection = connectionFactory.openConnection();
        Statement statement = connection.createStatement();
        statement.execute(
        		"UPDATE PRODUTOS SET " 
        		+ "NOME = '" + produto.getNome() + "'" + "," 
        		+ "DESCRICAO = '" + produto.getDescricao() + "'" + ","
        		+ "PRECO = " + produto.getPreco()
        		+ "WHERE ID = " + id);
      
        connection.commit();
        connection.close();
		return getById(id);
	}
	
	public Produto getById(int id) throws SQLException {
		Connection connection = connectionFactory.openConnection();
		Produto produto = new Produto();
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM PRODUTOS WHERE ID = " + id);
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()){
            produto.setId(resultSet.getInt("ID"));
            produto.setNome(resultSet.getString("NOME"));
            produto.setDescricao(resultSet.getString("DESCRICAO"));
            produto.setPreco(resultSet.getDouble("PRECO"));
        }
        connection.close();
		return produto;
	}
	
}
