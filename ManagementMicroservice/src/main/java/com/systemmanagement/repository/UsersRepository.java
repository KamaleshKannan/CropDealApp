package com.systemmanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.systemmanagement.model.Users;

public interface UsersRepository extends MongoRepository<Users,String> {
		  Optional<Users> findByUsername(String username);

		  Boolean existsByUsername(String username);

		  Boolean existsByEmail(String email);
		}

