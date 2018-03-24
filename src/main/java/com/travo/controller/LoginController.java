package com.travo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
public class LoginController {

    @GetMapping("/auth")
    public ResponseEntity authCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("Logged in!");
    }

//    @GetMapping("/home")
//    public ModelAndView homePage(ModelAndView model) {
//        model.setViewName("home");
//        return model;
//    }

    @GetMapping("/login")
    public ResponseEntity loginPage(@RequestParam(name = "error", required = false) String error) {
        if (error == null) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username or password was incorrect");

    }

}
