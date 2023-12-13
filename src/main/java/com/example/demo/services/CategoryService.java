package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoriaRepository;
	
	public List<Category> findAll() {
		return this.categoriaRepository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> categoria = this.categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria informada não encontrada."));
	}
	
	public Category create(Category category) {
		return this.categoriaRepository.save(category);
	}
	
	public Category update(Category category) {
		this.findById(category.getId());
		return this.categoriaRepository.save(category);
	}
	
	public void delete(Long id) {
		this.findById(id);
		this.categoriaRepository.deleteById(id);
	}
	
}
