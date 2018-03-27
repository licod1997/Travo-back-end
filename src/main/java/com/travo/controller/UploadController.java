package com.travo.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.travo.service.ProfileService;

@RestController
public class UploadController {
	@Autowired
	private ProfileService profileService;
	
	
	@PostMapping("/upimg")
	public String uploadFile(@RequestParam(name = "file") MultipartFile file) {
		String location=null;
		try {		
			byte[] bytes = file.getBytes();
			String timeStamp= getDateTime();
			String fileName= timeStamp +".jpg";
			
			Path path = Paths
					.get("D:\\Document\\eclipse-workspace\\Travo-back-end-master\\src\\main\\resources\\static\\images\\"
							+ fileName);
			Files.write(path, bytes);
			location= profileService.getImgUrl(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}
	
	private  final static String getDateTime()
	{
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
	    return timeStamp;
	}
}
