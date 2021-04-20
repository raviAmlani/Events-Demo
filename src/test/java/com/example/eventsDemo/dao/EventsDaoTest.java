package com.example.eventsDemo.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)
// @SpringBootTest
public class EventsDaoTest {

	@Autowired
	EventsRepository eventsRepository;
	
	@Autowired
	EventsDao eventsDao;

	// @Test
	public void test_insertEvent_valid() {
	    eventsDao.insertEvent(new Events("TEST-EVENT-1", 5, "server", "web", true));
	    Events event = eventsRepository.findById("TEST-EVENT-1").orElseGet(() -> new Events("Insert-failed", 5, "server", "web", true));
	    assertEquals("TEST-EVENT-1", event.getId());
	}
}
