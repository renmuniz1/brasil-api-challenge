package com.brasil.prev.challenge.repository.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.brasil.prev.challenge.model.customer.Customer;

@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class CustomerRepositoryTest {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	static final String CUSTOMER_NAME = "Renato Muniz2";
	static final String CUSTOMER_CPF = "28496356000";
	static final String CUSTOMER_ADDRESS = "Rua Rodrigues Lobo 180";
	
	private List<Customer> customers;
	
	@BeforeAll
	private void setUp() {
		
		Customer customer = new Customer(null, CUSTOMER_NAME, 
				CUSTOMER_CPF,CUSTOMER_ADDRESS);
		customerRepository.save(customer);
		
		customers = new ArrayList<>();
		
		customers.add(customer);
	}
	
	@Test
	public void testSave() {
		
		Customer customer = new Customer(null, CUSTOMER_NAME, 
				"81618937057",CUSTOMER_ADDRESS);
		
		Customer response = customerRepository.save(customer);
		customers.add(response);
		
		assertNotNull(response);
	}
	
	@Test
	public void testFindByCustomerCpf(){
		Optional<Customer> response = customerRepository.findByCpf(CUSTOMER_CPF);
		
		assertTrue(response.isPresent());
		assertEquals(CUSTOMER_CPF, response.get().getCpf());
	}
	
	@AfterAll
	private void tearDown() {
		customerRepository.deleteAll(customers);
	}

}
