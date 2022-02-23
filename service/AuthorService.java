package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Author;
import com.example.demo.repository.AuthorRepository;

@Service
public class AuthorService implements AuthorServiceImpl {

	@Autowired
	private AuthorRepository authRepo;

	@Override
	public List<Author> findAll() {
		// TODO Auto-generated method stub
		return authRepo.findAll() ;
	}

	@Override
	public Optional<Author> findById(int theId) {
		// TODO Auto-generated method stub
		return authRepo.findById(theId);
		
	}
	
	public CompletableFuture<Author> findByIdAsync(int theId) {
		
		return CompletableFuture.supplyAsync(() -> {
			Optional<Author> op= authRepo.findById(theId);
			if(op.isPresent()) {
				return op.get();
			}
			else {
				throw new RuntimeException("user not found");
			}
		});
	}

	@Override
	public Author save(Author author) {
		// TODO Auto-generated method stub
		return authRepo.save(author);
	}

	
	

}
