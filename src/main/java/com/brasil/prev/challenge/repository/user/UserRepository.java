package com.brasil.prev.challenge.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brasil.prev.challenge.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	Optional<User> findByEmail (String email);

}
