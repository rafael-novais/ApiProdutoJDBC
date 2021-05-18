package ultraje.domain.entity;

import static ultraje.dao.profile.ProfileDaoConstants.COLUMN_PROFILE_ID;
import static ultraje.dao.profile.ProfileDaoConstants.COLUMN_PROFILE_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	private String name;
	
	public Profile(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt(COLUMN_PROFILE_ID);
		this.name = resultSet.getString(COLUMN_PROFILE_NAME);
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
