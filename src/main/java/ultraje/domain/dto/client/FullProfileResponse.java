package ultraje.domain.dto.client;

import lombok.Data;

@Data
public class FullProfileResponse {
	private int id;
	private String name;
	private String nickname;
	private String email;
	private Double salary;
	private int account_number;
	private int credit_card;
}
