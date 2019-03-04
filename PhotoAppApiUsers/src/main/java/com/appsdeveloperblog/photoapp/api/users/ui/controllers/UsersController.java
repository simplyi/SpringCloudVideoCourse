package com.appsdeveloperblog.photoapp.api.users.ui.controllers;

 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.photoapp.api.users.ui.model.CreateUserRequestModel;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;

	@GetMapping("/status/check")
	public String status()
	{
		return "Working on port " + env.getProperty("local.server.port");
	}
 
	
	@PostMapping
	public String createUser(@RequestBody CreateUserRequestModel userDetails)
	{
		return "Create user method is called";
	}
}
