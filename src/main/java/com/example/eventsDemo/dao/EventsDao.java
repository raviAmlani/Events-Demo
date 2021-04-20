package com.example.eventsDemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsDao {

	@Autowired
	EventsRepository eventsRepository;
	
	public void insertEvent(Events event) {
		eventsRepository.save(event);
    }
	
}
