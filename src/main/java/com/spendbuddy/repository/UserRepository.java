package com.spendbuddy.repository;

import java.util.Optional;

import com.spendbuddy.entity.auth.User;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<User,Long>  {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
}
