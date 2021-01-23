package com.brasil.prev.challenge.service.customer.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brasil.prev.challenge.model.customer.Customer;
import com.brasil.prev.challenge.repository.customer.CustomerRepository;
import com.brasil.prev.challenge.service.customer.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		
		return customerRepository.findAll(pageable);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		
		return customerRepository.findById(id);
	}

	@Override
	public Customer save(Customer customer) {
		
		return customerRepository.save(customer);
	}

	@Override
	public void deleteById(Long id) {
		
		 customerRepository.deleteById(id);
		
	}

	@Override
	public Customer update(Customer customer) {
		
		return customerRepository.save(customer);
	}

}
