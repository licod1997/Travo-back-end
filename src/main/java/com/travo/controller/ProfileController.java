package com.travo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travo.dto.ProfileDTO;
import com.travo.service.ProfileService;

@CrossOrigin
@RestController
public class ProfileController {
	@Autowired
	private ProfileService profileService;

	@PostMapping("/prof/upload")
	public String uploadFile(@RequestParam(name = "file") MultipartFile file) {
		String location = null;
		try {
			byte[] bytes = file.getBytes();
			String timeStamp = profileService.getDateTime();
			String fileName = timeStamp + ".jpg";

			Path path = Paths.get("C:\\Users\\Notebook\\Desktop\\Travo\\0.0.1\\back-end\\src\\main\\resources\\static\\images" + fileName);

			Files.write(path, bytes);

			location = profileService.getImgUrl(fileName);
			System.out.println("123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}

	@PostMapping("/prof/update")
	public ResponseEntity UpdateUserInfo(@RequestBody ProfileDTO profileDTO, Authentication auth) {
		profileDTO.setUsername(auth.getName());
		{
			profileService.updateUserProfile(profileDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Update Success");
		}
	}


	@GetMapping("/prof/info")
	public ResponseEntity<ProfileDTO> loadUserDetail(Authentication auth) {
		return new ResponseEntity<>(profileService.loadUserProfile(auth.getName()), HttpStatus.OK);
	}
}
