package ultraje.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import ultraje.config.security.TokenService;
import ultraje.domain.entity.Client;
import ultraje.exception.ServiceException;
import ultraje.service.client.ClientService;

public class TokenAuthFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private ClientService clientService;
	
	public TokenAuthFilter(TokenService tokenService, ClientService clientService) {
		this.tokenService = tokenService;
		this.clientService = clientService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getToken(request);
		if(tokenService.isValid(token)) {
			try {
				authClient(token);				
			}catch (ServiceException e) {
				throw new ServletException("Error to found client by id!");
			}
		}
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");	
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) return null;
		return token.substring(7, token.length());
	}
	
	private void authClient(String token) throws ServiceException {
		Client client = clientService.getById(tokenService.getClientIdByToken(token));
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
	}

}
