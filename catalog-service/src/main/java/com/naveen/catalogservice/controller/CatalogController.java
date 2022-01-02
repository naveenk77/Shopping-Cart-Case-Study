package com.naveen.catalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naveen.catalogservice.model.IdGenerator;
import com.naveen.catalogservice.model.Product;
import com.naveen.catalogservice.repository.IdRepository;
import com.naveen.catalogservice.repository.ProductRepository;

@RestController
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	ProductRepository prodRepo;
	
	@Autowired
	IdRepository idRepo;
	
	@PostMapping("")
	public void addProduct(@RequestBody Product product) {
		IdGenerator idGen= idRepo.findById("prodId").get();
		product.setId(idGen.getSeq());
		idGen.setSeq(idGen.getSeq()+1);
		idRepo.save(idGen);
		prodRepo.save(product);
	}
	
	@GetMapping("")
	public List<Product> getAllProducts(){
		List<Product> list= new ArrayList<>();
		prodRepo.findAll().stream().forEach(list::add);
		return list;
	}
	
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") int id) {
		return prodRepo.findById(id).get();
	}
	
	@PutMapping("")
	public void updateProduct(@RequestBody Product product) {
		prodRepo.save(product);
	}
	

	@GetMapping("/get/{name}") //We Can Get retrieve  Catalog Details  By Using Name Of The Catalog  in Database
	public List<Product> getProductByName(@PathVariable("name") String name){
		 return prodRepo.findByName(name);
		 
	}

	@DeleteMapping("/{id}") //We Can Delete  Catalog Details  By Using Id Of The Catalog  in Database
	public void deleteProduct(@PathVariable("id") int id) {
		prodRepo.deleteById(id);
		
	}

	

	
}
