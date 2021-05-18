package ultraje.dao.client;

import static ultraje.dao.client.ClientDaoConstants.PARAM_CLIENT_ACCOUNT_NUMBER;
import static ultraje.dao.client.ClientDaoConstants.PARAM_CLIENT_CREDIT_CARD;
import static ultraje.dao.client.ClientDaoConstants.PARAM_CLIENT_EMAIL;
import static ultraje.dao.client.ClientDaoConstants.PARAM_CLIENT_ID;
import static ultraje.dao.client.ClientDaoConstants.PARAM_CLIENT_NAME;
import static ultraje.dao.client.ClientDaoConstants.PARAM_CLIENT_NICKNAME;
import static ultraje.dao.client.ClientDaoConstants.PARAM_CLIENT_PASSWORD;
import static ultraje.dao.client.ClientDaoConstants.PARAM_CLIENT_SALARY;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ultraje.domain.entity.Client;
import ultraje.exception.DAOException;
import ultraje.service.profile.ProfileService;

@Repository
public class ClientDao {
	
	@Autowired
	private NamedParameterJdbcTemplate sql;
	
	@Autowired
	private PlatformTransactionManager transactionManager;

	public Integer registerClient(Client client) throws DAOException {
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		MapSqlParameterSource parameters = new MapSqlParameterSource()
		.addValue(PARAM_CLIENT_NAME, client.getName())
	    .addValue(PARAM_CLIENT_NICKNAME, client.getNickname())
		.addValue(PARAM_CLIENT_EMAIL, client.getEmail())
		.addValue(PARAM_CLIENT_PASSWORD, client.getPassword())
		.addValue(PARAM_CLIENT_SALARY, client.getSalary())
		.addValue(PARAM_CLIENT_ACCOUNT_NUMBER, client.getAccountNumber() > 0 ? client.getAccountNumber() : null)
		.addValue(PARAM_CLIENT_CREDIT_CARD, client.getCreditCard() > 0 ? client.getCreditCard() : null);
		
		String query = "INSERT INTO client "
				+ "(name, nickname, email, psw, salary, account_number, credit_card_number) "
				+ "values (:client_name, :client_nickname, :client_email, :client_password, "
				+ ":client_salary, :client_account_number, :client_credit_card )";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
			
		try {
			this.sql.update(query, parameters, keyHolder, new String[]{"id"});
			int clientIdRegistred = (int) keyHolder.getKey().longValue();
			transactionManager.commit(status);
			return clientIdRegistred;
		}catch(Exception e) {
			transactionManager.rollback(status);
			throw new DAOException("Cant register client in database");
		}
	}
	
	public Client getByEmail(String email) throws DAOException {
		Map<String, Object> parameters = new HashMap<String, Object>(); 
		parameters.put(PARAM_CLIENT_EMAIL, email);
		try{
			Client client= sql.queryForObject(
				"SELECT * FROM client WHERE email = :client_email", 
				parameters,
				(resultSet, rowNum) -> new Client(resultSet));
			return client;
		}catch (Exception e) {
			throw new DAOException("Error to find client by email!");
		}
	}
	
	public Client getById(int id) throws DAOException {
		Map<String, Object> parameters = new HashMap<String, Object>(); 
		parameters.put(PARAM_CLIENT_ID, id);
		try{
			Client client= sql.queryForObject(
				"SELECT * FROM client WHERE id = :client_id", 
				parameters,
				(resultSet, rowNum) -> new Client(resultSet));
			return client;
		}catch (Exception e) {
			throw new DAOException("Error to find client by id!");
		}
	}
	
	public List<Client> getAllClients() throws DAOException {
		try{
			List<Client> clients = sql.query("SELECT * FROM client",
				(resultSet, rowNum) -> new Client(resultSet));
			return clients;
		}catch (Exception e) {
			throw new DAOException("Error to find clients");
		}
	}
	
}
