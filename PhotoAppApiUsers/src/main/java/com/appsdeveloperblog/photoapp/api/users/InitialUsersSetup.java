package com.appsdeveloperblog.photoapp.api.users;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitialUsersSetup {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info("From Application Ready Event");
		
	}

}
