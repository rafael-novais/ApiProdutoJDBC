package ultraje.domain.dto.client;

import lombok.Data;
import ultraje.domain.entity.Client;

@Data
public class ClientToList {
	private int id;
	private String name;
	
	public ClientToList(Client client) {
		this.id = client.getId();
		this.name = client.getName();
	}
}
