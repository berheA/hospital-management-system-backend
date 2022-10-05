package com.berheamare.hospitalmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.berheamare.hospitalmanagementsystem.models.User;
import com.berheamare.hospitalmanagementsystem.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path="api/")
//@AllArgsConstructor


public class UserController {
	@Autowired
	private UserService userService;
	
	
	
//	public RegistrationController(RegistrationService registrationService) {
//		//super();
//		this.registrationService = registrationService;
//	}


	
@PostMapping("register")
	public String register(@RequestBody User request) {
		
		return userService.register(request);
	
	}
	@GetMapping("confirm")
	public String confirm(@RequestParam("token") String token) {
		
		return userService.confirmToken(token);
	
	}
	@GetMapping("admin")
	public String adminPage() {
		
		return "welcome to Admin Page";
	
	}

}
