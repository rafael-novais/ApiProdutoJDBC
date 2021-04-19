package ultraje.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ultraje.domain.entity.Client;

@Service
public class TokenService {

	@Value("${ultraje.jwt.expiration}")
	private String expiration;
	
	@Value("${ultraje.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		
		Client loggedUser = (Client) authentication.getPrincipal();
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + Long.valueOf(expiration));
		
		String token = 
			Jwts.builder()
				.setIssuer("Ultraje API")
				.setSubject(loggedUser.getId().toString())
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
		
		return token;
	}
	
	public boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public int getClientIdByToken(String token) {
		return Integer.valueOf(
				Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject());
	}
	
}
