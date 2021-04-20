package com.example.eventsDemo.manager;

import static com.example.eventsDemo.constants.EventsDemoConstants.FILE_ACCESS_ERROR;
import static com.example.eventsDemo.constants.EventsDemoConstants.INVALID_FILE_CONTENT_ERROR;
import static com.example.eventsDemo.constants.EventsDemoConstants.INVALID_FILE_PATH_ERROR;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.eventsDemo.constants.EventsDemoConstants;
import com.example.eventsDemo.dao.Events;
import com.example.eventsDemo.dao.EventsDao;
import com.example.eventsDemo.exception.EventsDemoException;
import com.example.eventsDemo.exception.FileProcessingException;
import com.example.eventsDemo.model.LogEvents;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EventsManager {

	@Autowired
	EventsDao eventsDao;
	
	private ObjectMapper objectMapper;
	
	private ConcurrentHashMap<String, LogEvents> logEventsMap;
	
	public EventsManager() {
		super();
		objectMapper = new ObjectMapper();
		logEventsMap = new ConcurrentHashMap<>();
	}

	public void processEventsFile(String filePath) {
		
		Path path = validateFilePath(filePath);
		String lineHolder = null;
		
		try(BufferedReader br = Files.newBufferedReader(path)) {
		    for(String line; (line = br.readLine()) != null; ) {
		        if (line.endsWith("}")) {
		        	// System.out.println("-->"+(lineHolder == null ? line : lineHolder + line));
		        	processEvent(lineHolder == null ? line : lineHolder + line);
		        	lineHolder = null;
		        } else {
		        	lineHolder = lineHolder == null ? line : lineHolder + line;
		        }
		    }
		    if (lineHolder != null) {
		    	throw new FileProcessingException(INVALID_FILE_CONTENT_ERROR);
		    }
		} catch (IOException e) {
			e.printStackTrace();
			throw new EventsDemoException(FILE_ACCESS_ERROR);
		}
	}

	private void processEvent(String line) {
		try {
			LogEvents logEvent = objectMapper.readValue(line, LogEvents.class);
			if (logEventsMap.get(logEvent.getId()) == null) {
				logEventsMap.put(logEvent.getId(), logEvent);
			} else {
				LogEvents storedLogEvent = logEventsMap.get(logEvent.getId());
				if (logEvent.getState().equals("STARTED")) {
					logEvent.setDuration((int) (storedLogEvent.getTimestamp() - logEvent.getTimestamp()));
				} else {
					logEvent.setDuration((int) (logEvent.getTimestamp() - storedLogEvent.getTimestamp()));
				}
				storeEvent(logEvent);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new FileProcessingException(e);
		}
		
	}

	private void storeEvent(LogEvents logEvent) {
		Events eventEntity = convertToEventsEntity(logEvent);
		eventsDao.insertEvent(eventEntity);
	}

	private Events convertToEventsEntity(LogEvents logEvent) {
		return new Events(logEvent.getId(), logEvent.getDuration(), 
							logEvent.getType(), logEvent.getHost(), 
							logEvent.getDuration() > 4 ? true : false);
	}

	private Path validateFilePath(String filePath) {
		
		Path path = null;
		if (filePath == null || "".equals(filePath)) {
			throw new EventsDemoException(INVALID_FILE_PATH_ERROR);
		}
		path = Paths.get(filePath);
		
		if (!path.isAbsolute() || null == path.getFileName() || "".equals(path.getFileName().toString()) || !path.toFile().exists()) {
			throw new EventsDemoException(INVALID_FILE_PATH_ERROR);
		}
		
		return path;
	}

}
