package com.example.eventsDemo.manager;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.eventsDemo.dao.EventsDao;
import com.example.eventsDemo.exception.EventsDemoException;
import com.example.eventsDemo.exception.FileProcessingException;

@RunWith(MockitoJUnitRunner.class)
public class EventsManagerTest {

	@InjectMocks
	EventsManager eventsManager;
	
	@Mock
	EventsDao eventsDao;
	
	@Test
	public void test_processEventsFile_valid() throws URISyntaxException {
		
		Path path = Paths.get(getClass().getClassLoader()
			      .getResource("test-logfile.txt").toURI());
		String absolutePath = path.toAbsolutePath().toString();
		Mockito.doNothing().when(eventsDao).insertEvent(Mockito.any());
		
		eventsManager.processEventsFile(absolutePath);

		// 9 Events in the test file
	    Mockito.verify(eventsDao, Mockito.times(9)).insertEvent(Mockito.any());
	}
	
	@Test (expected = EventsDemoException.class)
	public void test_processEventsFile_blankFilePath() {
		eventsManager.processEventsFile("");
	}
	
	@Test (expected = EventsDemoException.class)
	public void test_processEventsFile_relativePath() {
		eventsManager.processEventsFile("test-logfile.txt");
	}
	
	@Test (expected = FileProcessingException.class)
	public void test_processEventsFile_invalidFileContent() throws URISyntaxException {
		
		Path path = Paths.get(getClass().getClassLoader()
			      .getResource("invalid-logfile.txt").toURI());
		
		eventsManager.processEventsFile(path.toAbsolutePath().toString());
	}
		
}
