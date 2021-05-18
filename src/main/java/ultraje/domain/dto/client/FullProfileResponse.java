package ultraje.domain.dto.client;

import java.util.List;

import lombok.Data;
import ultraje.domain.entity.Profile;

@Data
public class FullProfileResponse {
	private int id;
	private String name;
	private String nickname;
	private String email;
	private Double salary;
	private int accountNumber;
	private int creditCard;
	private List<Profile> profiles;
}
