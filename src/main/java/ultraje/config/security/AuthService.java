package ultraje.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ultraje.dao.client.ClientDao;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private ClientDao clientDao;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			return clientDao.getByEmail(email);			
		}catch(Exception e) {
			throw new UsernameNotFoundException("Incorrect login!");
		}
	}

}
