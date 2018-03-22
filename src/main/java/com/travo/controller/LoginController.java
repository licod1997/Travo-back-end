package com.travo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@RestController
public class LoginController {

    @GetMapping("/auth")
    public ResponseEntity authCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("Logged in!");
    }

    @GetMapping("/home")
    public ModelAndView homePage(ModelAndView model) {
        model.setViewName("home");
        return model;
    }

}
