package com.example.mongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongo.entity.Post;
import com.example.mongo.entity.User;
import com.example.mongo.repository.PostRepository;
import com.example.mongo.repository.UserRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Post findById(String id) {
		return repo.findById(id).get();
	}
	
	public Post insert(Post post) {
		post = repo.insert(post);
		User user = userRepo.findById(post.getAuthor().getId()).get();
		user.getPosts().add(post);
		userRepo.save(user);
		return post;
	}
	
	public Post update(Post post) {
		Post newPost = findById(post.getId());
		newPost.setTitle(post.getTitle());
		newPost.setBody(post.getBody());
		return repo.save(newPost);
	}
}
