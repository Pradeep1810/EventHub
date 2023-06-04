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

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategory {
	
	@Id
	private int id;
	
	private String subCategory;
	
	@OneToMany(mappedBy = "subCategory")
	@JsonIgnore
	List<Event> events;
	

}
