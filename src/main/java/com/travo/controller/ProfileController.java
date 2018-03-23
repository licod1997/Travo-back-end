package com.travo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travo.dto.ProfileDTO;
import com.travo.service.ProfileService;
import com.travo.service.ProfileServiceImpl;

@CrossOrigin
@RestController
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	@PostMapping("/profile")
	public String uploadFile(@RequestParam(name = "file") MultipartFile file) {
		String location = null;
		try {
			byte[] bytes = file.getBytes();
			String timeStamp = getDateTime();
			String fileName = timeStamp + ".jpg";

			Path path = Paths.get("D:\\Document\\Travo\\trunk\\src\\main\\resources\\images\\" + fileName);

			Files.write(path, bytes);

			location = path.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}

	@PostMapping("/prof")
	public ResponseEntity UpdateUserInfo(@RequestBody ProfileDTO profileDTO, Authentication auth) {
	
		profileDTO.setUsername(auth.getName());
		boolean success = profileService.updateUserProfile(profileDTO);
		if (success) {
			return ResponseEntity.status(HttpStatus.OK).body("Update success");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Update Failed");
	}

	private final static String getDateTime() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Timestamp(System.currentTimeMillis()));
		return timeStamp;
	}
}
