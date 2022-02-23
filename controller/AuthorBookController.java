package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.exception.BookDetailsNotFoundException;
import com.example.demo.exception.BooksNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.LoginService;

@RestController
public class AuthorBookController {

	@Autowired
	private AuthorService authService;

	@Autowired
	private BookService bookService;

	@Autowired
	private LoginService loginService;

	@GetMapping("/authors")
	public ResponseEntity<List<Author>> retrieveAll(
			@RequestHeader(required = false, value = "authorizeId") String authorizeId) {

		if (loginService.check(authorizeId)) {
			List<Author> listOfAuthors = authService.findAll();
			if (listOfAuthors.isEmpty()) {
				throw new UserNotFoundException(" no author details");
			}
			return ResponseEntity.ok(listOfAuthors);
		}
		return new ResponseEntity("not authorized", HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/author/{id}")
	public ResponseEntity<String> retrieveAuthor(@PathVariable int id,
			@RequestHeader(required = false, value = "authorizeId") String authorizeId) {
		if (loginService.check(authorizeId)) {
			Optional<Author> au = authService.findById(id);
			if (au.isPresent()) {
				return ResponseEntity.ok(au.get().getName());
			} else {
				throw new UserNotFoundException("id is " + id);
			}
		}
		return new ResponseEntity("not authorized", HttpStatus.UNAUTHORIZED);

	}

	

	@GetMapping("/books/{id}")
	public ResponseEntity<List<String>> retrieveBooks(@PathVariable int id,
			@RequestHeader(required = false, value = "authorizeId") String authorizeId) {
		
		if (loginService.check(authorizeId)) {
			List<String> books = bookService.findBooks(id);
			if (books.isEmpty()) {
				throw new BooksNotFoundException(" id of book is " + id);
			}
			return ResponseEntity.ok(books);
		}
		return new ResponseEntity("not authorized", HttpStatus.UNAUTHORIZED);

	}

	@PostMapping("/addAuthor")
	public ResponseEntity<Object> addAuthor(@Valid @RequestBody Author auth,@RequestHeader(required = false, value = "authorizeId") String authorizeId) {
		
		if (loginService.check(authorizeId)) {
			Author author = authService.save(auth);
			URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(author.getId())
					.toUri();
			return ResponseEntity.created(loc).build();
		}
		return new ResponseEntity("not authorized", HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/detailsOfBook/{bid}/{aid}")
	public ResponseEntity<String> getDetailsOfBook(@PathVariable int bid, @PathVariable int aid,
			@RequestHeader(required = false, value = "authorizeId") String authorizeId) {
		
		if (loginService.check(authorizeId)) {
			String bookDetails = bookService.findTitleOfBook(bid, aid);
			if (bookDetails == null) {
				throw new BookDetailsNotFoundException(" book id " + bid + " , author id " + aid);
			}
			return ResponseEntity.ok(bookDetails);
		}
		return new ResponseEntity("not authorized", HttpStatus.UNAUTHORIZED);
	}
	
	
		
	@GetMapping("/author/async/{id}")
	public Author retrieveAuthorAsync(@PathVariable int id) {
		try {

			var futureTask = authService.findByIdAsync(id);
			futureTask.thenApply((a) -> {
				System.out.println("Future Task Complete.");
				return null;
			});

			return futureTask.get();
		} catch (Exception ex) {
			return null;
		}
	}

}
