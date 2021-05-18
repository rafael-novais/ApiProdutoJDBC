package ultraje.service.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ultraje.dao.profile.ProfileDao;
import ultraje.domain.entity.Client;
import ultraje.domain.entity.Profile;
import ultraje.exception.ServiceException;

@Service
public class ProfileService {

	@Autowired
	private ProfileDao profileDao;
	
	public void setClientProfiles(Client client) throws ServiceException {
		try {
			client.setProfiles(profileDao.getProfilesByClientId(client.getId()));			
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("Cannot set profiles in client " + client.getId());
		}
	}

	public List<Profile> getAllProfiles() throws ServiceException {
		try {
			return profileDao.getAllProfiles();		
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("Cannot get profiles!");
		}
	}
	
	
}
