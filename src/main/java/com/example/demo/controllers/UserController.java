package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.JwtTokenDto;
import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.UserCreateDto;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
    private UserService userService;
	
	@PostMapping("/login")
    public ResponseEntity<JwtTokenDto> authenticateUser(@RequestBody @Valid  LoginDto login) {
		JwtTokenDto token = this.userService.authenticateUser(login);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateDto user) {
        this.userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
	
}
