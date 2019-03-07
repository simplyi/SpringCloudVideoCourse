package com.appsdeveloperblog.photoapp.api.users.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.photoapp.api.users.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.data.*;

@Service
public class UsersServiceImpl implements UsersService {
	
	UsersRepository usersRepository;
	
	@Autowired
	public UsersServiceImpl(UsersRepository usersRepository)
	{
		this.usersRepository = usersRepository;
	}
 
	@Override
	public UserDto createUser(UserDto userDetails) {
		// TODO Auto-generated method stub
		
		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper(); 
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
		userEntity.setEncryptedPassword("test");
		
		
		usersRepository.save(userEntity);
 
		return null;
	}

}
