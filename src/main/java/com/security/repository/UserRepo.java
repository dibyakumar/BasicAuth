package com.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.security.entity.User;

public interface UserRepo extends MongoRepository<User, String>{

}
