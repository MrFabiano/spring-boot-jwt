package com.construcao.api.controller;

import com.construcao.api.model.UserData;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public void login(@RequestBody UserData userData){
        userData.setPassword(bCryptPasswordEncoder.encode(userData.getPassword()));
    }

}
