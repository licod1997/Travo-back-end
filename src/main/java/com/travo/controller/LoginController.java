package com.travo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @GetMapping("/auth")
    @ResponseBody
    public ResponseEntity authCheck() {
        return ResponseEntity.status(HttpStatus.OK).body("Logged in!");
    }

    @GetMapping("/login")
    public ModelAndView loginPage(ModelAndView model) {
        model.setViewName("login");
        return model;
    }

    @GetMapping("/home")
    public ModelAndView homePage(ModelAndView model) {
        model.setViewName("home");
        return model;
    }

}
