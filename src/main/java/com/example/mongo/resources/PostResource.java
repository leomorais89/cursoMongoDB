package com.example.mongo.resources;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.mongo.dto.CommentDTO;
import com.example.mongo.entity.Post;
import com.example.mongo.resources.util.URL;
import com.example.mongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@PostMapping
	public ResponseEntity<Post> insert(@RequestBody Post post) {
		post = service.insert(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).body(post);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {
		post.setId(id);
		post = service.update(post);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value = "/{id}/comments")
	public ResponseEntity<List<CommentDTO>> findByPost(@PathVariable String id) {
		List<CommentDTO> comments = service.findByPost(id);
		return ResponseEntity.ok().body(comments);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<Post>> find(@RequestParam(value = "text", defaultValue = "") String text, @RequestParam(value = "minDate", defaultValue = "") String minDate, @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		text = URL.urlDecode(text);
		Instant dataIni = URL.convertDate(minDate, Instant.EPOCH);
		Instant dataFim = URL.convertDate(maxDate, Instant.now());
		List<Post> posts = service.find(text, dataIni, dataFim);
		return ResponseEntity.ok().body(posts);
	}
}
