package com.yuqmettal.sum.usersservice.controller;

import com.yuqmettal.sum.usersservice.entity.User;
import com.yuqmettal.sum.usersservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<EntityModel<User>> signUp(@RequestBody User user){
        userService.signUp(user);
        return new ResponseEntity<EntityModel<User>>(HttpStatus.OK);
    }

}