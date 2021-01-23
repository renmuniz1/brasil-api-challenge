package com.brasil.prev.challenge.service.customer;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brasil.prev.challenge.model.customer.Customer;

public interface CustomerService {
	
	Page<Customer> findAll(Pageable pageable);
	
	Optional<Customer> findById(Long id);
	
	Customer save(Customer customer);
	
	void deleteById(Long id);
	
	Customer update(Customer customer);

}
