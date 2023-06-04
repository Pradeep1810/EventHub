package com.eventhub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventhub.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
