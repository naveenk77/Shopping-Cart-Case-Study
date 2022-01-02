package com.naveen.catalogservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.naveen.catalogservice.controller.CatalogController;
import com.naveen.catalogservice.model.Product;
import com.naveen.catalogservice.repository.ProductRepository;

@SpringBootTest
class CatalogServiceApplicationTests {
	
	@Mock
	private ProductRepository prodRepo;

	@InjectMocks
	private CatalogController contr;
	
	public List<Product> product;
	
	@Test 
	@Order(1)
	void getAllProductsTest() { //Testing on GetAll Prdocuts Method to check whether its working properly or not
		when(prodRepo.findAll()).thenReturn(Stream
				.of(new Product(2, "Note 8", "Electronics", 65465, "data.jpg", "Samsung galaxy note 8"),
					new Product(64, "Levi", "Clothing", 3599, "levi.jpg", "Printed Tshirt")).collect(Collectors.toList()));
		assertEquals(2,contr.getAllProducts().size());
	}
	
	@Test 
	@Order(2)
	public void getProductByIdTest(){ //Testing on Get Product Details By using Id Method
		Product product = new Product (2, "Note 8", "Electronics", 65465, "data.jpg", "Samsung galaxy note 8");
		Optional<Product> op = Optional.of(product);
		when(prodRepo.findById(2)).thenReturn(op);
		assertEquals(2,op.get().getId());
	}
	
	@Test 
	@Order(3)
	public void TestFindByName(){ //Testing on Get Product Details By Name Id Method
		List<Product> product = new ArrayList<Product>();
		product.add(new Product (2, "Note 8", "Electronics", 65465, "data.jpg", "Samsung galaxy note 8"));
		when(prodRepo.findByName("Note 8")).thenReturn(product);
		assertEquals(1, prodRepo.findByName("Note 8").size());
	}
	
	
	@Test 
	@Order(4)
	public void addProduct(){ //Testing on Add Product Details To database

		Product product = new Product (2, "Note 8", "Electronics", 65465, "data.jpg", "Samsung galaxy note 8");
		when(( prodRepo).insert(product)).thenReturn(product);
        assertEquals(product, ( prodRepo).insert(product));
	}
}
	
/*	@Test 
	@Order(5)
	public void updateProduct(){ //Testing on Update Product Details To database
		Product product = new Product (2, "Note 8", "Electronics", 65465, "data.jpg", "Samsung galaxy note 8");
		when(contr.updateProduct( product)).thenReturn(product);
		assertEquals(product,contr.updateProduct(product));
	}
	
	@Test 
	@Order(6)
	public void deleteProduct() //Testing on Delete Product Details By id
	{
		Product product = new Product (2, "Note 8", "Electronics", 65465, "data.jpg", "Samsung galaxy note 8");
		when(( contr).deleteProduct(2)).thenReturn(product);
        assertEquals(product, ( contr).deleteProduct(2));
	}
}*/



