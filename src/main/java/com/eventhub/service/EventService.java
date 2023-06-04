package com.eventhub.service;

import java.util.List;

import com.eventhub.exceptions.ApiException;
import com.eventhub.payloads.EventDto;

public interface EventService {
	
	EventDto createEvent(EventDto eventDto,Long userId);
	
	EventDto getEvent(Long eventId);
	
	List<EventDto> allEvents(Integer pageNumber , Integer pageSize);
	
	void deletEvent(Long eventId);
	
	EventDto updateEvent(EventDto eventDto , Long userId , Long eventId) throws ApiException;

}
