package com.brasil.prev.challenge.service.customer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.brasil.prev.challenge.model.customer.Customer;
import com.brasil.prev.challenge.repository.customer.CustomerRepository;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
public class CustomerServiceTest {
	
	
	@Autowired
	private CustomerService service;

	@MockBean
	private CustomerRepository repository;
	
	static final String CUSTOMER_CPF = "27378123056";
	
	@Test
	@Order(1)
	public void testSave() {
		
		BDDMockito.given(repository.save(Mockito.any(Customer.class)))
			.willReturn(getMockCustomer());
		Customer response = service.save(new Customer());
		
		assertNotNull(response);
	}
	
	@Test
	@Order(2)
	public void testFindByAccountNumber(){
		
		BDDMockito.given(repository.findByCpf(Mockito.anyString()))
			.willReturn(Optional.ofNullable(new Customer()));

		Optional<Customer> response = service.findByCpf(CUSTOMER_CPF);
		assertTrue(!response.isEmpty());
	}
	
	@AfterAll
	private void tearDown() {
		repository.deleteAll();
	}
	
	private Customer getMockCustomer() {
		return new Customer(1l,"Renato Muniz10", 
				CUSTOMER_CPF ,"Rua Rodrigues Lobo 200");
	}

}
