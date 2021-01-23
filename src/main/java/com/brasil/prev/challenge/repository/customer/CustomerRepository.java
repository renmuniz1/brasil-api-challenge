package com.brasil.prev.challenge.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brasil.prev.challenge.model.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
