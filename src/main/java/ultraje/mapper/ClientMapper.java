package ultraje.mapper;

import org.springframework.stereotype.Component;

import ultraje.domain.dto.client.ClientToRegister;
import ultraje.domain.dto.client.FullProfileResponse;
import ultraje.domain.entity.Client;

@Component
public class ClientMapper {

	public FullProfileResponse clientToFullProfileResponse(Client client) {
		FullProfileResponse response = new FullProfileResponse();
		response.setId(client.getId());
		response.setName(client.getName());
		response.setEmail(client.getEmail());
		response.setNickname(client.getNickname());
		response.setSalary(client.getSalary());
		response.setAccountNumber(client.getAccountNumber());
		response.setCreditCard(client.getAccountNumber());
		return response;
	}
	
	public Client registerDtoToClient(ClientToRegister clientDto) {
		Client client = new Client();
		client.setName(clientDto.getName());
		client.setNickname(clientDto.getNickname());
		client.setEmail(clientDto.getEmail());
		client.setPassword(clientDto.getPassword());
		client.setSalary(clientDto.getSalary());
		client.setAccountNumber(clientDto.getAccountNumber());
		client.setCreditCard(clientDto.getCreditCard());
		return client;
	}
	
}