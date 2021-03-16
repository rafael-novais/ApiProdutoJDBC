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

import ultraje.domain.dto.client.ClientToRegister;
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
	public ResponseEntity<?> registerUser(@RequestBody ClientToRegister clientToRegister, 
			UriComponentsBuilder uriBuilder) {
		Integer clientIdRegistred;
		try {
			clientIdRegistred = clientService.registerClient(clientMapper.registerDtoToClient(clientToRegister));
		}catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		URI uri = uriBuilder.path("/clients/{id}").buildAndExpand(clientIdRegistred).toUri();
		return ResponseEntity.created(uri).body(clientIdRegistred);
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
