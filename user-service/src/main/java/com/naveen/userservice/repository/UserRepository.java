package com.naveen.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.naveen.userservice.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer>{
	User findByUsername(String username);
}
