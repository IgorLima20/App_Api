package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.BookDto;
import com.example.demo.models.Book;
import com.example.demo.services.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("livro")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public ResponseEntity<List<Book>> findAll() {
		List<Book> book = this.bookService.findAll();
		return new ResponseEntity<List<Book>>(book, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Book> findById(@PathVariable Long id) {
		Book book = this.bookService.findById(id);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Book> create(@RequestBody @Valid BookDto book) {
		Book bookNew = this.bookService.create(book);
		return new ResponseEntity<Book>(bookNew, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody @Valid BookDto book) {
		book.setId(id);
		Book bookUpdate = this.bookService.update(book);
		return new ResponseEntity<Book>(bookUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Book> delete(@PathVariable Long id) {
		this.bookService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
