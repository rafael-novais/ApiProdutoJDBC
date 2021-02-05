package com.example.demo.BD;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Component
public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(
					"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("poli1340");
		dataSource = comboPooledDataSource;
	}
	
    public Connection openConnection() throws SQLException{
    	Connection connection = dataSource.getConnection();
    	connection.setAutoCommit(false);
    	return connection;
    }
}