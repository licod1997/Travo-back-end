package com.travo.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.travo.dto.ProfileDTO;
import com.travo.model.User;
import com.travo.repository.UserRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private UserRepository userRepository;

	private Long getUserID(String username) {
		long id = 0;
		User user = new User();
		user = userRepository.findByUsername(username);
		id = user.getId();
		return id;
	}

	@Override
	public ProfileDTO loadUserProfile(String username) {
		User user = new User();
		ProfileDTO profileDTO = new ProfileDTO();
		long id = getUserID(username);
		user = userRepository.findOne(id);
		profileDTO.setUsername(user.getUsername());
		profileDTO.setFullName(user.getFullName());
		profileDTO.setImgURL(user.getImageUrl());

		return profileDTO;
	}

	@Override
	public User updateUserProfile(ProfileDTO profileDTO) {
		User user = new User();
		
			user = userRepository.findByUsername(profileDTO.getUsername());
			user.setFullName(profileDTO.getFullName());
			user.setImageUrl(profileDTO.getImgURL());
					
		return userRepository.save(user);
	}
	@Override
	public String getImgUrl(String fileName) {
		String location = null;		
		try {
			File file = new ClassPathResource("static/avatars/"+fileName).getFile();
			location= file.getAbsolutePath();
		} catch (IOException ex) {
			
			ex.printStackTrace();
		}  
		return location;
	}
	@Override
	public String getDateTime() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Timestamp(System.currentTimeMillis()));
		return timeStamp;
	}
}
