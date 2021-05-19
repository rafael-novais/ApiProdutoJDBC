package ultraje.dao.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ultraje.domain.entity.Client;
import ultraje.exception.DAOException;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
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
