	package com.eventhub.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eventhub.entities.Category;
import com.eventhub.entities.Event;
import com.eventhub.entities.SubCategory;
import com.eventhub.entities.User;
import com.eventhub.exceptions.ApiException;
import com.eventhub.exceptions.ResourceNotFoundException;
import com.eventhub.payloads.EventDto;
import com.eventhub.repositories.CategoryRepository;
import com.eventhub.repositories.EventRepository;
import com.eventhub.repositories.SubCategoryRepository;
import com.eventhub.repositories.UserRepository;
import com.eventhub.service.EventService;



@Service
public class EventServiceImpl implements EventService {

	@Autowired
	ModelMapper modelMap;

	@Autowired
	EventRepository eventRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CategoryRepository catRepo;
	
	@Autowired
	SubCategoryRepository subCategoryRepo;

	@Override
	public EventDto createEvent(EventDto eventDto , Long userId) {
		
		User userExist = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		Category categoryExist = this.catRepo.findById(eventDto.getCategory().getId()).
				orElseThrow(() -> new ResourceNotFoundException("Category", "Id", eventDto.getCategory().getId()));
		
		SubCategory subCategoryCheck = this.subCategoryRepo.findById(eventDto.getSubCategory().getId()).
				orElseThrow(() -> new ResourceNotFoundException("Sub-Category", "index", eventDto.getSubCategory().getId()));
		
		
		Event event = this.dtoToEvent(eventDto);
		
		event.setModerator(userExist);
		
		event.setCategory(categoryExist);
		
		event.setSubCategory(subCategoryCheck);
		
		

		Event eventCreated = this.eventRepo.save(event);

		return this.eventToDto(eventCreated);
	}

	@Override
	public EventDto getEvent(Long eventId) {

		Event event = this.eventRepo.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));

		return this.eventToDto(event);
	}

	@Override
	public void deletEvent(Long eventId) {

		Event checkEvent = this.eventRepo.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));

		this.eventRepo.delete(checkEvent);

	}

	@Override
	public List<EventDto> allEvents(Integer pageNumber, Integer pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Event> eventPages = this.eventRepo.findAll(p);

		List<Event> allEvents = eventPages.getContent();
		
		
		List<EventDto> allEventsDto = allEvents.stream().map((e) -> this.modelMap.map(e, EventDto.class))
				.collect(Collectors.toList());

		return allEventsDto;
	}

	private Event dtoToEvent(EventDto eventDto) {

		Event event = this.modelMap.map(eventDto, Event.class);

		return event;

	}

	private EventDto eventToDto(Event event) {

		EventDto eventDto = this.modelMap.map(event, EventDto.class);

		return eventDto;

	}

	@Override
	public EventDto updateEvent(EventDto eventDto, Long userId , Long eventId) throws ApiException {
        
		User userCheck = this.userRepo.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
				
		Event eventExist = this.eventRepo.findById(eventId).
				orElseThrow(() -> new ResourceNotFoundException("Event", "Id", eventId));
		
		User moderator = eventExist.getModerator();
		
		if(moderator.equals(userCheck)) {
			
			eventExist.setEventName(eventDto.getEventName());
			
			eventExist.setEventDesc(eventDto.getEventDesc());
			
			eventExist.setEventDate(eventDto.getEventDate());
			
			eventExist.setEventImage(eventDto.getEventImage());
			
			eventExist.setTagline(eventDto.getTagline());
			
			eventExist.setRigour_rank(eventDto.getRigour_rank());
			
			Event savedUpdatedEvent = this.eventRepo.save(eventExist);
			
			EventDto eventDtoUpdated = this.modelMap.map(savedUpdatedEvent, EventDto.class);
			
			eventDtoUpdated.setEdited("Edited");
			
			return eventDtoUpdated;
					
		}else {
			
			throw new ApiException("User is not moderator");
		}
		
		
	}

}
