//package com.example.mongo.config;
//
//import java.time.Instant;
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//import com.example.mongo.dto.AuthorDTO;
//import com.example.mongo.dto.CommentDTO;
//import com.example.mongo.entity.Post;
//import com.example.mongo.entity.User;
//import com.example.mongo.repository.PostRepository;
//import com.example.mongo.repository.UserRepository;
//
//@Configuration
//public class ConfigTest implements CommandLineRunner {
//
//	@Autowired
//	private UserRepository userRepo;
//	
//	@Autowired
//	private PostRepository postRepo;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		
//		userRepo.deleteAll();
//		postRepo.deleteAll();
//		
//		User maria = new User(null, "Maria Brown", "maria@gmail.com");
//		User alex = new User(null, "Alex Green", "alex@gmail.com");
//		User bob = new User(null, "Bob Grey", "bob@gmail.com");
//		
//		userRepo.saveAll(Arrays.asList(maria, alex, bob));
//		
//		Post p1 = new Post(null, Instant.parse("2019-10-20T22:40:00Z"), "Partiu Viagem", "Vou viajar para São Paulo", new AuthorDTO(maria));
//		Post p2 = new Post(null, Instant.parse("2019-10-10T22:40:00Z"), "Viagem", "Vou viajar para Argentina", new AuthorDTO(bob));
//		
//		postRepo.saveAll(Arrays.asList(p1, p2));
//		
//		maria.getPosts().add(p1);
//		bob.getPosts().add(p2);
//		
//		userRepo.saveAll(Arrays.asList(maria, bob));
//		
//		CommentDTO c1 = new CommentDTO(null, "Boa viagem irmão", Instant.parse("2019-10-20T23:40:00Z"), p1, new AuthorDTO(alex));
//		CommentDTO c2 = new CommentDTO(null, "Divirta-se", Instant.parse("2019-10-10T23:40:00Z"), p2, new AuthorDTO(alex));
//		
//		p1.getComments().add(c1);
//		p2.getComments().add(c2);
//		
//		postRepo.saveAll(Arrays.asList(p1, p2));
//	}
//
//}
