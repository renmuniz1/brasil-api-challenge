package com.brasil.prev.challenge.service.user.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brasil.prev.challenge.model.user.User;
import com.brasil.prev.challenge.repository.user.UserRepository;
import com.brasil.prev.challenge.service.user.UserService;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
