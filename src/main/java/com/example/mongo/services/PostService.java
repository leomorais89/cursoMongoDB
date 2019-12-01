package com.example.mongo.services;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongo.dto.CommentDTO;
import com.example.mongo.entity.Post;
import com.example.mongo.entity.User;
import com.example.mongo.repository.PostRepository;
import com.example.mongo.repository.UserRepository;
import com.example.mongo.services.exception.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Post findById(String id) {
		try {
			return repo.findById(id).get();
		}
		catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	public Post insert(Post post) {
		post = repo.insert(post);
		User user = userRepo.findById(post.getAuthor().getId()).get();
		user.getPosts().add(post);
		userRepo.save(user);
		return post;
	}
	
	public void deleteById(String id) {
		Post post = findById(id);
		User user = userRepo.findById(post.getAuthor().getId()).get();
		user.getPosts().remove(post);
		userRepo.save(user);
		repo.deleteById(id);
	}
	
	public Post update(Post post) {
		Post newPost = findById(post.getId());
		newPost.setTitle(post.getTitle());
		newPost.setBody(post.getBody());
		return repo.save(newPost);
	}
	
	public List<CommentDTO> findByPost(String id) {
		try {
			Post post = repo.findById(id).get();
			List<CommentDTO> comments = post.getComments();
			return comments;
		}
		catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	public List<Post> find(String text, Instant minDate, Instant maxDate) {
		return repo.findByText(text, minDate, maxDate); 
	}
}
