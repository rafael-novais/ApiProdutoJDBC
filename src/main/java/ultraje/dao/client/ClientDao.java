package ultraje.dao.client;

import static ultraje.dao.client.ClientDaoConstants.PARAM_CLIENT_EMAIL;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import ultraje.domain.entity.Client;
import ultraje.exception.DAOException;

@Repository
public class ClientDao {
	
	@Autowired
	private NamedParameterJdbcTemplate sql;
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	public Client getByEmail(String email) throws DAOException, SQLException {
		Map<String, Object> parameters = new HashMap<String, Object>(); 
		parameters.put(PARAM_CLIENT_EMAIL, email);
		try{
			Client client= sql.queryForObject(
				"SELECT * FROM client WHERE email = :client_email", 
				parameters,
				(resultSet, rowNum) -> new Client(
						resultSet.getInt("id"), 
						resultSet.getString("name"),
						resultSet.getString("nickname"),
						resultSet.getString("email"),
						resultSet.getString("psw"),
						resultSet.getDouble("salary"),
						resultSet.getInt("account_number"),
						resultSet.getInt("credit_card_number")));
			return client;
		}catch (Exception e) {
			throw new DAOException("Error to find client by email!");
		}
	}
	
}
