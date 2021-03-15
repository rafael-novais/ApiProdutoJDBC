package ultraje.domain.dto.client;

import lombok.Data;

@Data
public class ClientRegister {
	private String name;
	private String nickname;
	private String email;
	private String password;
	private Double salary;
}
