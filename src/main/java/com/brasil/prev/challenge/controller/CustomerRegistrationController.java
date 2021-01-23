package com.brasil.prev.challenge.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.brasil.prev.challenge.dto.model.customer.CustumerDTO;
import com.brasil.prev.challenge.model.customer.Customer;
import com.brasil.prev.challenge.service.customer.CustomerService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("customer/v1/")
public class CustomerRegistrationController {
	
	CustomerService customerService;
	
	@Autowired
	public CustomerRegistrationController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/customers")
	public Page<CustumerDTO> listCustomers(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
		
		Page<Customer> customers = customerService.findAll(pageable);
		Page<CustumerDTO> itemsDTO  = customers.map(t -> t.convertEntityToDTO());
		return itemsDTO;
		
	}
	
	
	@PostMapping
	@Transactional
	@ApiOperation(value = "Route to register a customer")
	public ResponseEntity<CustumerDTO> register(@RequestBody  @Valid CustumerDTO dto, UriComponentsBuilder uriBuilder) {
		Customer customer = dto.convertDTOToEntity();
		customerService.save(customer);
		
		URI uri = uriBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(uri).body(customer.convertEntityToDTO());
		
	}
	
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Route to receive a customer")
	public ResponseEntity<CustumerDTO> find(@PathVariable Long id) {
		Optional<Customer> customer = customerService.findById(id);
		if (customer.isPresent()) {
			return ResponseEntity.ok(customer.get().convertEntityToDTO());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Route to alter a customer")
	public ResponseEntity<CustumerDTO> update(@PathVariable Long id, @RequestBody  @Valid CustumerDTO dto) {
		Optional<Customer> optional = customerService.findById(id);
		if (optional.isPresent()) {
			Customer customer = dto.convertDTOToEntity();
			customer = customerService.update(customer);
			CustumerDTO custumerDTOReturn = customer.convertEntityToDTO();
			return ResponseEntity.ok(custumerDTOReturn);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Route to delete a customer")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Customer> optional = customerService.findById(id);
		if (optional.isPresent()) {
			customerService.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	

}
