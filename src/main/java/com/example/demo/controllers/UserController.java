package com.example.demo.controllers;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.JwtTokenDto;
import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.UserCreateDto;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> userAll() {
        List<User> user = this.userService.findAll();
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
	
	@PostMapping("/login")
    public ResponseEntity<JwtTokenDto> authenticateUser(@RequestBody @Valid LoginDto login) {
		JwtTokenDto token = this.userService.authenticateUser(login);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserCreateDto user) {
        this.userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	
}