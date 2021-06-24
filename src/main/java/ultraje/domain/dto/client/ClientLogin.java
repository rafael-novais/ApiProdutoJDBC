package ultraje.domain.dto.client;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;	

@Data
public class ClientLogin {

	private String email;
	private String password;
	
	public UsernamePasswordAuthenticationToken getUPAT() {
		return new UsernamePasswordAuthenticationToken(this.email, this.password);
	}
	
}
