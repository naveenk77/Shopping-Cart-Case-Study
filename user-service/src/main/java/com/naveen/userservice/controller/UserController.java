package com.naveen.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.userservice.model.IdGenerator;
import com.naveen.userservice.model.User;
import com.naveen.userservice.repository.IdRepository;
import com.naveen.userservice.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	IdRepository idRepo;
	
	@PostMapping("")
	public void addUser(@RequestBody User user) {
		IdGenerator idGen= idRepo.findById("userId").get();
		user.setId(idGen.getSeq());
		idGen.setSeq(idGen.getSeq()+1);
		idRepo.save(idGen);
		user.setRole("Customer");
		userRepo.save(user);
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers(){
		List<User> users= new ArrayList<>();
		userRepo.findAll().forEach(users::add);
		return users;
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") int id) {
		return userRepo.findById(id).get();
	}
	
	@PutMapping("")
	public void updateUser(@RequestBody User user) {
		userRepo.save(user);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		userRepo.deleteById(id);
	}
	
}
