package com.eventhub.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.eventhub.entities.User;
import com.eventhub.exceptions.SQLIntegrityViolationException;
import com.eventhub.payloads.UserDto;
import com.eventhub.repositories.UserRepository;
import com.eventhub.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ModelMapper modelMap;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		
		try {
		
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
		
		}catch (DataIntegrityViolationException e) {
			// TODO Auto-generated catch block
			throw new SQLIntegrityViolationException("Email already exists.");
		}
	
	}

	@Override
	public UserDto getUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
     private User dtoToUser(UserDto userDto) {
    	 
    	 
    	 
    	 User user = this.modelMap.map(userDto, User.class);
    	 	  
    	 return user;
     }
     
     private UserDto userToDto(User user) {
    	 
    	 
    	 UserDto userDto = this.modelMap.map(user, UserDto.class);
    	 return userDto;
     }
}
