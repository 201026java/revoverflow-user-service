package com.revature.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.DTOs.RSSUserDTO;
import com.revature.models.User;
import com.revature.services.RSSService;
import com.revature.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(
		origins = { "*" }, 
		methods = { RequestMethod.GET, RequestMethod.PUT, 
					RequestMethod.PATCH, RequestMethod.POST },
		allowedHeaders = { "*" }
	)
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	RSSService rssService;
	

	@GetMapping
	@PreAuthorize("hasAuthority('USER')")
	public List<User> getAllUser(){
		return userService.getAllUser();
	}
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		
		return userService.getUserById(id);
	}
	
	@PostMapping("/authorize")
	public ResponseEntity<User> authUser(@RequestBody User user){
		return rssService.getUserByEmail(user.getEmail());
	}
	
	@PostMapping("/roles")
	public Collection<GrantedAuthority> getRoles(@RequestBody User user){
		return rssService.getAuthority(user);
	}
	
	@PostMapping("/login") 
	public ResponseEntity<User> loginUser(@RequestBody RSSUserDTO user) { 
		return rssService.login(user); 
	}
	

}