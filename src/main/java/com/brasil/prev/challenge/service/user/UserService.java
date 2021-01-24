package com.brasil.prev.challenge.service.user;

import java.util.Optional;

import com.brasil.prev.challenge.model.user.User;

public interface UserService {
	
	Optional<User> findByEmail(String email);

}
