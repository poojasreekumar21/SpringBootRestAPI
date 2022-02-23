package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query(value="select title from book b where b.authorid= :aid ",nativeQuery=true)
	public List<String> findByAuthorId(@Param("aid")int id);
	
	@Query(value="select title from book b where b.book_id= :bid and b.authorid= :aid",nativeQuery=true)
	public String findByBookIdAndAuthorId(@Param("bid")int bookId,@Param("aid")int authId);
	
}
