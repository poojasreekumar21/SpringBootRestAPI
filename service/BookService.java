package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService implements BookServiceImpl {

	@Autowired
	private BookRepository bookRepo;


	
	public List<String> findBooks(int id) {
		// TODO Auto-generated method stub
		return bookRepo.findByAuthorId(id);
	}
	
	public String findTitleOfBook(int bookId,int authId) {
		return bookRepo.findByBookIdAndAuthorId(bookId, authId);
	}


	
	

}
