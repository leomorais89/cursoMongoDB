package com.example.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mongo.entity.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
