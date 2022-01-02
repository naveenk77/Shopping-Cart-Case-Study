package com.naveen.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.userservice.model.AuthenticationRequest;
import com.naveen.userservice.model.AuthenticationResponse;
import com.naveen.userservice.model.IdGenerator;
import com.naveen.userservice.model.User;
import com.naveen.userservice.repository.IdRepository;
import com.naveen.userservice.repository.UserRepository;
import com.naveen.userservice.security.JwtUtil;
import com.naveen.userservice.service.MyUserDetailsService;

@RestController
@RequestMapping("/user")
public class SecurityController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	IdRepository idRepo;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username and password", e);
		}
		
		final UserDetails userDetails= myUserDetailsService.loadUserByUsername(authRequest.getUsername());
		 
		final String jwt= jwtUtil.generateToken(userDetails);
		System.out.println(jwt);
		
		User user= userRepo.findByUsername(userDetails.getUsername());
		
		return 	ResponseEntity.ok(new AuthenticationResponse(jwt, user.getId(), user.getName()));
	}
	
	
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		System.out.println(user);
		List<String> usernames= new ArrayList<>();
		userRepo.findAll().forEach(u-> usernames.add(u.getUsername()));
		
		if(usernames.contains(user.getUsername()))
			return "-1";
		
		IdGenerator idGen= idRepo.findById("userId").get();
		user.setId(idGen.getSeq());
		idGen.setSeq(idGen.getSeq()+1);
		idRepo.save(idGen);
		user.setRole("USER");
		userRepo.save(user);
		
		return user.getId()+"";
		
	}
	
}
