package com.example.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mongo.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
