package com.eventhub.payloads;

import java.util.List;


import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	
	private String emailId;
	
	private String password;
	
	
	private String sex;
	
	
	private int age;
	
	@ManyToMany
	List<Long> events;
	
}
