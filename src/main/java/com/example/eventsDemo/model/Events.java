package com.example.eventsDemo.model;

public class Events {

	private String id;
	private String type;
	private String host;
	private String state;
	private long timestamp;
	
	public Events() {
		super();
	}
	
	public Events(String id, String type, String host, String state, long timestamp) {
		super();
		this.id = id;
		this.type = type;
		this.host = host;
		this.state = state;
		this.timestamp = timestamp;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
