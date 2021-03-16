package ultraje.domain.dto.client;

import lombok.Data;

@Data
public class ClientToRegister {
	private String name;
	private String nickname;
	private String email;
	private String password;
	private Double salary;
	private Integer accountNumber;
	private Integer creditCard;
}
