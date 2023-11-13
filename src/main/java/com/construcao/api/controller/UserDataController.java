package com.construcao.api.controller;

import com.construcao.api.model.UserData;
import com.construcao.api.service.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserDataController {


    private final UserDetailsServiceImpl userDetailsService;

    public UserDataController(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/all-user")
    public List<UserData> listUserData(){
        return userDetailsService.listUser();
    }


    @RequestMapping("/status")
    public String userView(){
        return "online";
    }
}
