package ultraje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ultraje.domain.dto.client.ClientToList;
import ultraje.domain.entity.Profile;
import ultraje.service.profile.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

	@Autowired
	private ProfileService profileService; 

	@GetMapping
	public ResponseEntity<?> getAllProfiles(UriComponentsBuilder uriBuilder) {
		try {
			List<Profile> profiles = profileService.getAllProfiles();
			return ResponseEntity.ok().body(profiles);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
