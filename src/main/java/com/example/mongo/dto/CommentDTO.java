package com.example.mongo.dto;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.example.mongo.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CommentDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String text;
	private Instant data;
	
	@DBRef
	@JsonIgnore
	private Post post;
	private AuthorDTO author;
	
	public CommentDTO() {
		
	}

	public CommentDTO(String id, String text, Instant data, Post post, AuthorDTO author) {
		super();
		this.id = id;
		this.text = text;
		this.data = data;
		this.post = post;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Instant getData() {
		return data;
	}

	public void setData(Instant data) {
		this.data = data;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}
}
