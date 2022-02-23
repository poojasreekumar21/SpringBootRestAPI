package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	
	
	@ManyToOne(targetEntity=Author.class)
    @JoinColumn(name = "authorid")
	private int authorid;
	
	public Book() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAuthor() {
		return authorid;
	}
	public void setAuthor(int authorId) {
		this.authorid = authorId;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", authorid=" + authorid + "]";
	}
	public Book(int id, String title, int authorId) {
		super();
		this.id = id;
		this.title = title;
		this.authorid = authorId;
	}
	
	
	
	
}
