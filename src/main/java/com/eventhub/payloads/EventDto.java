package com.eventhub.payloads;

import java.time.LocalDateTime;
import java.util.List;

import com.eventhub.entities.Category;
import com.eventhub.entities.SubCategory;
import com.eventhub.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    
    
    private String eventName;
    
    
    private String tagline;
    
    
    private String eventDesc;
    
    
    private LocalDateTime eventDate;
    
    
    private String eventImage;
    
    
    private int rigour_rank;
    
    private User moderator;
    
    private List<Long> attendees;
    
    private Category category;
    
    private SubCategory subCategory;

    private String edited;
}
