package com.eventhub.entities;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long eventId;
    
    @Column
    private String eventName;
    
    @Column
    private String tagline;
    
    @Column
    private String eventDesc;
    
    @Column
    private LocalDateTime eventDate;
    
    @Column
    private String eventImage;
    
    @Column
    private int rigour_rank;
    
    
    @ManyToOne
    private User moderator;
    
    // an event can belong to only one category but a category can belong to multiple events  
    
    @ManyToOne
    private Category category;
    
    @ManyToOne
    private SubCategory subCategory;
    
    private String edited;
    
    @ManyToMany
    @JoinTable(name = "event_attendees",
    joinColumns = @JoinColumn(name = "event_id" , referencedColumnName = "eventId"),
    inverseJoinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "userId"))
    List<User> attendees;
}
