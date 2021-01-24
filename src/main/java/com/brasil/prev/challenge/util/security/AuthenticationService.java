package com.brasil.prev.challenge.util.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brasil.prev.challenge.model.user.User;
import com.brasil.prev.challenge.service.user.UserService;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private UserService userService ;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.findByEmail(username);
		
		if(user.isPresent()) {
			return user.get();
		}
		throw new UsernameNotFoundException("Invalida params!!");
	}

}
