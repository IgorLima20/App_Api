package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Book;
import com.example.demo.models.ImagesBook;
import com.example.demo.services.ImagesBookService;


@RestController
@RequestMapping("livro/foto")
public class ImagesBookController {
	
	@Autowired
	private ImagesBookService imagesBookService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<List<ImagesBook>> findByBook(@PathVariable Long id) {
		List<ImagesBook> imagesBook = this.imagesBookService.findByBook(id);
		return new ResponseEntity<List<ImagesBook>>(imagesBook, HttpStatus.OK);
	}
	
	@GetMapping("/arquivo/{fileName}")
	public ResponseEntity<Resource> getImagem(@PathVariable String fileName) {
	    Resource resource = this.imagesBookService.getImage(fileName);
	    return ResponseEntity.ok().header("content-type", "image/*").body(resource);	
	}
	
	@PostMapping(value="/{id}")
	public ResponseEntity<Book> create(@PathVariable Long id, MultipartFile file) {
		this.imagesBookService.create(id, file);
		return new ResponseEntity<Book>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Book> delete(@PathVariable Long id) {
		this.imagesBookService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
