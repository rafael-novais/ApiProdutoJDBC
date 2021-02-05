package com.example.demo.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class ConnectionFactory {
    public Connection openConnection() throws SQLException{
    	Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC",
            "root",
            "poli1340"
        );
    	connection.setAutoCommit(false);
    	return connection;
    }
}