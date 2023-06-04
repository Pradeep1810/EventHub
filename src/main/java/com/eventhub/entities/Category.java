package com.eventhub.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
	
	@Id
	private int id;
	
	private String category;
	
	@JsonIgnore
	@OneToMany(mappedBy ="category")
	List<Event> events;
	
	

}
