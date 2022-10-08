package com.berheamare.hospitalmanagementsystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.berheamare.hospitalmanagementsystem.models.JwtRequest;
import com.berheamare.hospitalmanagementsystem.models.JwtResponse;
import com.berheamare.hospitalmanagementsystem.models.User;
import com.berheamare.hospitalmanagementsystem.repositories.UserRepository;
import com.berheamare.hospitalmanagementsystem.services.JwtService;
import com.berheamare.hospitalmanagementsystem.services.UserService;

//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="hms/")
//@AllArgsConstructor


public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;
	
	
	
//	public RegistrationController(RegistrationService registrationService) {
//		//super();
//		this.registrationService = registrationService;
//	}


	
@PostMapping("/createUser")
	public String register(@RequestBody User request) {
		
		return userService.register(request);
	
	}
	@GetMapping("/confirmToken")
	public String confirm(@RequestParam("token") String token) {
		
		return userService.confirmToken(token);
	
	}
	@GetMapping("/adminPage")
	public String adminPage() {
		
		return "welcome to Admin Page";
	
	}
	
  @PostMapping({"/authenticate"})
	// It was User2
public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
return jwtService.createJwtToken(jwtRequest);
}
  
  
//  @GetMapping({"/forAdmin"})
//  @PreAuthorize("hasRole('Admin')")
//  public String forAdmin(){
//      return "This URL is only accessible to the admin";
//  }
//
//  @GetMapping({"/forUser"})
//  @PreAuthorize("hasRole('User')")
//  public String forUser(){
//      return "This URL is only accessible to the user";
//  }

}
