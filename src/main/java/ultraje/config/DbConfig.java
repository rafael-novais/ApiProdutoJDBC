package ultraje.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import ultraje.util.constants.UltrajeDBConstants;

@Configuration
public class DbConfig {
	
	@Bean 
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource());
	}
	
	@Bean 
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl(UltrajeDBConstants.connection_string);
		comboPooledDataSource.setUser(UltrajeDBConstants.bd_user);
		comboPooledDataSource.setPassword(UltrajeDBConstants.bd_password);
		return comboPooledDataSource;
	}
}