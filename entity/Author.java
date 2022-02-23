package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name="author")
public class Author {

	@Id
	@Column(name="author_id")
	@Positive(message=" Id should be a positive value")
	private int id;
		
	@Column(name="name")
	@NotBlank(message="Author name should not be null or empty ")
	@Size(min=2,message="Min 2 characters for Author name")
	@Pattern(regexp="^[a-zA-Z]*$",message="Name should contain characters only")
	private String name;
	
	public Author() {
		
	}
	
	public Author(int id, String name) {
		this.id = id;
		this.name = name;
		
	}
	
	public int getId() {
		return id;
	}
		
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name +  "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
