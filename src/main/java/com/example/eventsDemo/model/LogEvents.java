package com.example.eventsDemo.model;

public class LogEvents extends Events {

	private int duration;
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public LogEvents() {
	}

	public LogEvents(String id, String type, String host, String state, long timestamp, int duration) {
		super(id, type, host, state, timestamp);
		this.duration = duration;
	}

}
