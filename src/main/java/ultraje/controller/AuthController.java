package ultraje.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ultraje.domain.dto.client.ClientLogin;

@RestController("/auth")
public class AuthController {

	@PostMapping
	public ResponseEntity<?> authenticate(@RequestBody @Valid ClientLogin login){
		return ResponseEntity.ok().body(login);
	}
	
}
