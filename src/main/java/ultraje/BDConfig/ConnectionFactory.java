package ultraje.BDConfig;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import ultraje.conf.UltrajeConstants;

@Component
public class ConnectionFactory {
	
	public DataSource dataSource;
	
	public ConnectionFactory() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(UltrajeConstants.connection_string);
		comboPooledDataSource.setUser(UltrajeConstants.bd_user);
		comboPooledDataSource.setPassword(UltrajeConstants.bd_password);
		dataSource = comboPooledDataSource;
	}
	
    public Connection openConnection() throws SQLException{
    	Connection connection = dataSource.getConnection();
    	connection.setAutoCommit(false);
    	return connection;
    }
}