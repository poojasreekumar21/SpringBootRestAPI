package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Author;

public interface AuthorServiceImpl {
	
	public List<Author> findAll();
	
	public Optional<Author> findById(int theId);
	
	public Author save(Author author);
	
	

}
