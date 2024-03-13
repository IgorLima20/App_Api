package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.BookDto;
import com.example.demo.models.Book;
import com.example.demo.models.Category;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public List<Book> findAll() {
		return this.bookRepository.findAll();
	}
	
	public Book findById(Long id) {
		Optional<Book> book = this.bookRepository.findById(id);
		return book.orElseThrow(() -> new ObjectNotFoundException("Livro informado não encontrado."));
	}
	
	public Book create(BookDto book) {
		Category category = this.categoryService.findById(book.getCategory_id());
		Book bookNew = new Book(book.getId(), book.getTitle(), book.getDescription(), book.getReleaseDate(), category);
		return this.bookRepository.save(bookNew);
	}
	
	public Book update(BookDto book) {
		this.findById(book.getId());
		Category category = this.categoryService.findById(book.getCategory_id());
		Book bookUpdate = new Book(book.getId(), book.getTitle(), book.getDescription(), book.getReleaseDate(), category);
		return this.bookRepository.save(bookUpdate);
	}
	
	public void delete(Long id) {
		this.findById(id);
		this.bookRepository.deleteById(id);
	}
	
}
