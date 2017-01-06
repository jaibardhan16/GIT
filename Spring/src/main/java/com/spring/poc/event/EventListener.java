package com.spring.poc.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
public class EventListener implements ApplicationListener<ContactEvent> {
	private Logger LOGGER = Logger.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(ContactEvent event) {
		LOGGER.debug("Receiving event: " + event.getEventType());

	}

}
