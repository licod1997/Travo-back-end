package com.travo.controller;

import com.travo.dto.SignupDTO;
import com.travo.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SignupController {

    private SignupService signupService;

    @Autowired
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public ResponseEntity signupNewUser(@RequestBody SignupDTO signupDTO) {
        if (signupService.findUser(signupDTO) == null) {
            signupService.saveUser(signupDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Signed in");
        }
        return ResponseEntity.status(HttpStatus.OK).body("This username has been used");
    }

}
