package ultraje.service.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ultraje.dao.client.ClientDao;
import ultraje.domain.entity.Client;
import ultraje.exception.ServiceException;
import ultraje.service.profile.ProfileService;

@Service
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ProfileService profileService;
	
	public Integer registerClient(Client client) throws ServiceException {
		try {
			return clientDao.registerClient(client);
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Client getByEmail(String email) throws ServiceException {
		try {
			Client client = clientDao.getByEmail(email);
			profileService.setClientProfiles(client);
			return client;
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public Client getById(int id) throws ServiceException {
		try {
			Client client = clientDao.getById(id);
			profileService.setClientProfiles(client);
			return client;
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
	public List<Client> getAllClients() throws ServiceException {
		try {
			return clientDao.getAllClients();
		}catch (Exception e) {
			throw new ServiceException(e.getMessage());
		}
	}
	
}
