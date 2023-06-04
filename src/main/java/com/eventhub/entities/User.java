package com.eventhub.entities;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User" , uniqueConstraints = {
		
		@UniqueConstraint(columnNames = {"emailId"}) 
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	@Column
	private String emailId;
	
	@Column
	private String password;
	
	@Column
	private String sex;
	
	@Column
	private int age;
	
	@JsonIgnore
	@OneToMany(mappedBy = "moderator")
	List<Event> event;
	
	@ManyToMany
	@JsonIgnore
	List<Event> events;
	
}
