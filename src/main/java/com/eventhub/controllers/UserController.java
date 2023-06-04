package com.eventhub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventhub.payloads.UserDto;
import com.eventhub.service.UserService;

@RestController
@RequestMapping("/api/v3/app")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
		
		
		UserDto savedUserDto = this.userService.createUser(userDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
	}

}
