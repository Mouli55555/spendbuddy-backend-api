package com.spendbuddy.repository;

import java.util.Optional;

import com.spendbuddy.entity.auth.ERole;
import com.spendbuddy.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(ERole name);

}
