package ultraje.dao.profile;

import static ultraje.dao.profile.ProfileDaoConstants.PARAM_CLIENT_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import ultraje.domain.entity.Profile;
import ultraje.exception.DAOException;

@Repository
public class ProfileDao {

	@Autowired
	private NamedParameterJdbcTemplate sql;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	public List<Profile> getProfilesByClientId(int clientId) throws DAOException{
		Map<String, Object> parameters = new HashMap<String, Object>(); 
		parameters.put(PARAM_CLIENT_ID, clientId);
		try{
			List<Profile> clients = sql.query(
					"SELECT * FROM client_profile cp INNER JOIN profile p ON p.id = cp.profile_id WHERE cp.client_id = :client_id",
					parameters,
					(resultSet, rowNum) -> new Profile(resultSet));
			return clients;
		}catch (Exception e) {
			throw new DAOException("Error to find profiles by clientId!");
		}
	}
	
	public List<Profile> getAllProfiles() throws DAOException{
		try{
			List<Profile> clients = sql.query(
					"SELECT * FROM profile",
				(resultSet, rowNum) -> new Profile(resultSet));
			return clients;
		}catch (Exception e) {
			throw new DAOException("Error to find profiles by clientId!");
		}
	}
	
}
