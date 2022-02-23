package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;

public interface BookServiceImpl {
	
	public List<String> findBooks(int id);
	
	public String findTitleOfBook(int bookId,int authId);
	



}
