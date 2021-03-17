package ultraje.domain.dto.client;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClientToRegister {
	@NotNull @NotEmpty
	private String name;
	@NotNull @NotEmpty
	private String nickname;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String password;
	private Double salary;
	private Integer accountNumber;
	private Integer creditCard;
}
