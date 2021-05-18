package ultraje.domain.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import static ultraje.dao.client.ClientDaoConstants.COLUMN_CLIENT_ID;
import static ultraje.dao.client.ClientDaoConstants.COLUMN_CLIENT_NAME;
import static ultraje.dao.client.ClientDaoConstants.COLUMN_CLIENT_NICKNAME;
import static ultraje.dao.client.ClientDaoConstants.COLUMN_CLIENT_EMAIL;
import static ultraje.dao.client.ClientDaoConstants.COLUMN_CLIENT_PASSWORD;
import static ultraje.dao.client.ClientDaoConstants.COLUMN_CLIENT_SALARY;
import static ultraje.dao.client.ClientDaoConstants.COLUMN_CLIENT_ACCOUNT_NUMBER;
import static ultraje.dao.client.ClientDaoConstants.COLUMN_CLIENT_CREDIT_CARD;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private String name;
	private String nickname;
	private String email;
	private String password;
	private Double salary;
	private Integer accountNumber;
	private Integer creditCard;
	private List<Profile> profiles = new ArrayList<>();;
	
	public Client(ResultSet resultSet) throws SQLException {
		this.id = resultSet.getInt(COLUMN_CLIENT_ID);
		this.name = resultSet.getString(COLUMN_CLIENT_NAME);
		this.nickname = resultSet.getString(COLUMN_CLIENT_NICKNAME);
		this.email = resultSet.getString(COLUMN_CLIENT_EMAIL);
		this.password = resultSet.getString(COLUMN_CLIENT_PASSWORD);
		this.salary = resultSet.getDouble(COLUMN_CLIENT_SALARY);
		this.accountNumber = resultSet.getInt(COLUMN_CLIENT_ACCOUNT_NUMBER);
		this.creditCard = resultSet.getInt(COLUMN_CLIENT_CREDIT_CARD);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.profiles;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getEmail();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
