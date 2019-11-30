package com.example.mongo.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mongo.entity.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{ $and: [ { date: {$gte: ?1} }, { date: {$lte: ?2} }, {$or: [{'title': {$regex: ?0, $options: 'i'}}, {'body': {$regex: ?0, $options: 'i'}}, {'comments.text': {$regex: ?0, $options: 'i'} } ] } ] }")
	List<Post> findByText(String text, Instant minDate, Instant maxDate);
}
