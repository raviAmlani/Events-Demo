package com.example.eventsDemo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.eventsDemo.manager.EventsManager;

@SpringBootApplication
public class EventsDemoApplication implements CommandLineRunner {

	@Autowired
	EventsManager eventsManager;
	
	public static void main(String[] args) {
		SpringApplication.run(EventsDemoApplication.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {

        System.out.println("----- Execution started");
        
        System.out.println("Provide absolute path to the logfile with extension (in the next line):");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        sc.close();
        System.out.println(filePath);
        
        eventsManager.processEventsFile(filePath);
        
        System.out.println("----- Execution finished");
        
    }

}
