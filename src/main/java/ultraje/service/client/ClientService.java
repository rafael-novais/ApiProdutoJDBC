package ultraje.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ultraje.dao.client.ClientDao;
import ultraje.domain.entity.Client;
import ultraje.exception.ServiceException;

@Service
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	
	public Integer registerClient(Client client) throws ServiceException {
		try {
			return clientDao.registerClient(client);
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Client getByEmail(String email) throws ServiceException {
		try {
			return clientDao.getByEmail(email);
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
}
