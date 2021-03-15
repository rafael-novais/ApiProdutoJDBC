package ultraje.domain.entity;

import lombok.Data;

@Data
public class Client {

	private int id;
	private String name;
	private String nickname;
	private String email;
	private String password;
	private Double salary;
	private int account_number;
	private int credit_card;
}
