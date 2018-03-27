package com.travo.service;

import com.travo.dto.ProfileDTO;
import com.travo.model.User;

public interface ProfileService {
	ProfileDTO loadUserProfile(String username);
	User updateUserProfile(ProfileDTO profileDTO);
	String getDateTime();
	String getImgUrl(String fileName);
}
