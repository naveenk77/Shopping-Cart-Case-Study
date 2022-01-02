package com.naveen.orderservice.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.naveen.orderservice.model.IdGenerator;

@Repository
public interface IdRepository extends MongoRepository<IdGenerator, String>{

}
