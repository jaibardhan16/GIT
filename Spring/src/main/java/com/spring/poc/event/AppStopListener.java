package com.spring.poc.event;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppStopListener implements ApplicationListener<ContextStoppedEvent> {
	private Logger LOGGER = Logger.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(ContextStoppedEvent arg0) {
		LOGGER.debug("Application sucessfully stoped  at : " + new Date());

	}

}
