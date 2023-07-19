package com.appsdeveloperblog.photoapp.api.users;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.photoapp.api.users.data.AuthorityEntity;
import com.appsdeveloperblog.photoapp.api.users.data.AuthorityRepository;

import jakarta.transaction.Transactional;

@Component
public class InitialUsersSetup {
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@EventListener
	public void onApplicationEvent(ApplicationReadyEvent event) {
		logger.info("From Application Ready Event");
		
		AuthorityEntity readAuthority = createAuthority("READ");
		AuthorityEntity writeAuthority = createAuthority("WRITE");
		AuthorityEntity deleteAuthority = createAuthority("DELETE");
		
	}
	
	@Transactional
	private AuthorityEntity createAuthority(String name) {
		
		AuthorityEntity authority = authorityRepository.findByName(name);
		
		if(authority == null) {
			authority = new AuthorityEntity(name);
			authorityRepository.save(authority);
		}
		
		return authority;
	}

}
