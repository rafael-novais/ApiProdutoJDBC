package ultraje.domain.dto.client;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;	

@Data
public class ClientLogin {

	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String password;
	
	public UsernamePasswordAuthenticationToken getUPAT() {
		return new UsernamePasswordAuthenticationToken(this.email, this.password);
	}
	
}
