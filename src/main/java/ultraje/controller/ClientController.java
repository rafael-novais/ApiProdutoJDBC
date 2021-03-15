package ultraje.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ultraje.domain.dto.client.ClientRegister;
import ultraje.domain.dto.client.FullProfileResponse;
import ultraje.mapper.ClientMapper;
import ultraje.service.client.ClientService;

@RequestMapping("/clients")
@RestController
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientMapper clientMapper;

	@PostMapping
	public ResponseEntity<?> registerUser(@RequestBody ClientRegister clientToRegister, UriComponentsBuilder uriBuilder) {
		URI uri = uriBuilder.path("/client/{id}").buildAndExpand(clientToRegister.getName()).toUri();
		return ResponseEntity.created(uri).body(clientToRegister);
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<?> getClientByEmail(@PathVariable String email, UriComponentsBuilder uriBuilder) {
		try {
			FullProfileResponse client = 
					clientMapper.clientToFullProfileResponse(clientService.getByEmail(email));
			return ResponseEntity.ok().body(client);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
}
