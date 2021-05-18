package ultraje.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ultraje.dao.client.ClientDao;
import ultraje.service.client.ClientService;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	private ClientService clientService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			return clientService.getByEmail(email);			
		}catch(Exception e) {
			throw new UsernameNotFoundException("Incorrect login!");
		}
	}

}
