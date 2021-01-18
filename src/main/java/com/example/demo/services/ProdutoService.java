package com.example.demo.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.BD.ConnectionFactory;
import com.example.demo.model.Produto;

@Service
public class ProdutoService {
	
	ConnectionFactory connectionFactory;
	
	@Autowired
	public ProdutoService(ConnectionFactory connectionFactory) {
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
	
}
