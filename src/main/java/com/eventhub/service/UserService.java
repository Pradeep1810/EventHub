package com.eventhub.service;

import com.eventhub.payloads.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto userDto);
	
	UserDto getUser(Long userId);
	
	

}
