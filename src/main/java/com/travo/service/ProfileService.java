package com.travo.service;

import com.travo.dto.ProfileDTO;

public interface ProfileService {
	ProfileDTO loadUserProfile(String username);
	boolean updateUserProfile(ProfileDTO profileDTO);
	String getDateTime();
	String getImgUrl(String fileName);
}
