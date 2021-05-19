package ultraje.dao.client;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ultraje.domain.entity.Client;
import ultraje.exception.DAOException;

@SpringBootTest
public class ClientDaoTest {

	@Autowired
	private ClientDao clientDao;
	
	@Test
	public void shouldGetClientByEmail() throws DAOException {
		String email = "rafael@gmail.com";
		Client client = clientDao.getByEmail(email);
		assertNotNull(client);
		assertEquals(email, client.getEmail());
	}
	
}
