package com.example.mongo.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongo.entity.User;
import com.example.mongo.repository.UserRepository;
import com.example.mongo.services.exception.ResourceNotFoundException;
import com.example.mongo.services.exception.SaveException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		try {
			return repo.findById(id).get();
		}
		catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	public User insert(User user) {
		if(user.getNome() == null)
			throw new SaveException("Usuário não pode ser vazio!");
		return repo.insert(user);
	}
	
	public void deleteById(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User user) {
		User newUser = findById(user.getId());
		newUser.setNome(user.getNome());
		newUser.setEmail(user.getEmail());
		if(newUser.getNome() == null)
			throw new SaveException("Usuário não pode ser vazio");
		return repo.save(newUser);
	}
}
