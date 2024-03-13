package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Book;
import com.example.demo.models.ImagesBook;
import java.util.List;


public interface ImagesBookRepository extends JpaRepository<ImagesBook, Long> {
	List<ImagesBook> findByBook(Book book);
}
