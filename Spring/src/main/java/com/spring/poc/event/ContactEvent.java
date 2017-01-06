package com.spring.poc.event;

import org.springframework.context.ApplicationEvent;

public class ContactEvent extends ApplicationEvent {

	private static final long serialVersionUID = -6494684258221507358L;

	public ContactEvent(Object source, String eventData, String eventType) {
		super(source);
		this.eventData = eventData;
		this.eventType = eventType;
	}

	private String eventData;

	public String getEventData() {
		return eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	private String eventType;

}
