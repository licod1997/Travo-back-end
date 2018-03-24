package com.travo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travo.dto.ProfileDTO;
import com.travo.model.User;
import com.travo.repository.UserRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private UserRepository userRepository;
	
	private Long getUserID(String username) {
		long id=0;
		User user = new User();
		user= userRepository.findByUsername(username);
		id= user.getId();
		return id;
	}
	
	@Override
	public ProfileDTO loadUserProfile(String username) {
		User user = new User();
		ProfileDTO profileDTO = new ProfileDTO();
		long id= getUserID(username);
		user = userRepository.findOne(id);
		profileDTO.setUsername(user.getUsername());
		profileDTO.setFullName(user.getFullName());
		profileDTO.setImgURL(user.getImageUrl());
		
		return profileDTO;
	}
	
	@Override
	public boolean updateUserProfile(ProfileDTO profileDTO) {
		User user= new User();	
		if(profileDTO != null) {
			user= userRepository.findByUsername(profileDTO.getUsername());
			user.setFullName(profileDTO.getFullName());
			user.setImageUrl(profileDTO.getImgURL());		
			userRepository.save(user);
			return true;
		}		
		return false;
	}
}
