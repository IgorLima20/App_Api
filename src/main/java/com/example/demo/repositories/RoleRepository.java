package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Role;
import com.example.demo.models.enums.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName role);
}
