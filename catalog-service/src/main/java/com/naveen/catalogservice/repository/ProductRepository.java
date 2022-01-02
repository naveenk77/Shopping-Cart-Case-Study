package com.naveen.catalogservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.naveen.catalogservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer>{

	List<Product> findByName(String string);



}
