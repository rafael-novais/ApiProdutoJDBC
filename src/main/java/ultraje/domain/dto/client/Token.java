package ultraje.domain.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
	
	public String token;
	public String type;
	
}
