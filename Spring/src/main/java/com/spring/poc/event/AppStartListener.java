package com.spring.poc.event;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class AppStartListener implements ApplicationListener<ContextStartedEvent> {
	private Logger LOGGER = Logger.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(ContextStartedEvent arg0) {
		LOGGER.debug("Application sucessfully Started at : " + new Date());

	}

}
