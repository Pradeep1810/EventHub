package com.eventhub.controllers;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eventhub.entities.User;
import com.eventhub.exceptions.ApiException;
import com.eventhub.exceptions.ResourceNotFoundException;
import com.eventhub.payloads.EventDto;
import com.eventhub.repositories.UserRepository;
import com.eventhub.service.EventService;
import com.eventhub.service.FileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v3/app")
public class EventController {

	@Autowired
	EventService eventService;

	@Autowired
	ObjectMapper objectMap;

	@Autowired
	FileService fileService;
	
	@Autowired
	UserRepository userRepo;

	@Value("${event.contentImage}")
	String path;

	@PostMapping("/events/{moderatorId}")
	public ResponseEntity<EventDto> addEvent(@RequestParam("eventImage") MultipartFile file,
			@RequestParam("eventDto") String eventDto , @PathVariable Long moderatorId) throws JsonMappingException, JsonProcessingException {

		
		User userExist = this.userRepo.findById(moderatorId).
				orElseThrow(() -> new ResourceNotFoundException("User", "Id", moderatorId));
		
		EventDto event = this.objectMap.readValue(eventDto, EventDto.class);

		if (!file.isEmpty()) {

			String imgName = this.fileService.uploadFile(path, file);
			
			event.setEventImage(imgName);
			
		}
		
		EventDto savedEvent = this.eventService.createEvent(event,moderatorId);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
		
	}
	
	@PutMapping("/events/{moderatorId}/{eventId}")
	public ResponseEntity<EventDto> updatedEvent(@RequestParam("eventImage") MultipartFile file,
			@RequestParam("eventDto") String eventDto , @PathVariable Long moderatorId , @PathVariable Long eventId) throws JsonMappingException, JsonProcessingException, ApiException {

		
		User userExist = this.userRepo.findById(moderatorId).
				orElseThrow(() -> new ResourceNotFoundException("User", "Id", moderatorId));
		
		EventDto event = this.objectMap.readValue(eventDto, EventDto.class);

		if (!file.isEmpty()) {

			String imgName = this.fileService.uploadFile(path, file);
			
			event.setEventImage(imgName);
			
		}
		
		EventDto savedEvent = this.eventService.updateEvent(event,moderatorId,eventId);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
		
	}
	
	

	@GetMapping("/events/{id}")
	public ResponseEntity<EventDto> getEvent(@PathVariable Long id) {

		EventDto event = this.eventService.getEvent(id);

		return ResponseEntity.status(HttpStatus.OK).body(event);
	}

	@GetMapping("/events")
	public ResponseEntity<List<EventDto>> getAllEvent(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "limit", defaultValue = "5") Integer limit,
			@RequestParam(value = "sortBy", defaultValue = "asc", required = false) String sortBy) {

		List<EventDto> event = this.eventService.allEvents(page, limit);

		if (sortBy.equalsIgnoreCase("asc")) {

			event.sort(Comparator.comparing(EventDto::getEventDate));

		} else {

			event.sort(Comparator.comparing(EventDto::getEventDate).reversed());

		}

		return ResponseEntity.status(HttpStatus.OK).body(event);
	}

	@DeleteMapping("/events/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable Long id) {

		this.eventService.deletEvent(id);

		return ResponseEntity.status(HttpStatus.OK).body("Event Sucessfully Deleted");
	}

}
