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

import com.example.demo.models.Category;
import com.example.demo.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("categoria")
public class CategoryController {
	
	@Autowired
	private CategoryService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> categoria = this.categoriaService.findAll();
		return new ResponseEntity<List<Category>>(categoria, HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category categoria = this.categoriaService.findById(id);
		return new ResponseEntity<Category>(categoria, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Category> create(@RequestBody @Valid Category categoria) {
		Category categoriaNew = this.categoriaService.create(categoria);
		return new ResponseEntity<Category>(categoriaNew, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody @Valid Category categoria) {
		categoria.setId(id);
		Category categoriaUpdate = this.categoriaService.update(categoria);
		return new ResponseEntity<Category>(categoriaUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Category> delete(@PathVariable Long id) {
		this.categoriaService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
