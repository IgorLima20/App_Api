package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.services.exceptions.ForeignKeyViolationException;
import com.example.demo.services.exceptions.ObjectExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Category> findAllPage(Pageable page) {
		return this.categoriaRepository.findAll(page);
	}
	
	public Category findById(Long id) {
		Optional<Category> categoria = this.categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria informada não encontrada."));
	}
	
	public Category create(Category category) {
		if (this.existsByCategory(category.getCategory())) {
			throw new ObjectExistException("Categoria já cadastrada com o mesmo nome.");
		}
		return this.categoriaRepository.save(category);
	}
	
	public Category update(Category category) {
		this.findById(category.getId());
		if (this.existsByCategory(category.getCategory())) {
			throw new ObjectExistException("Categoria já cadastrada com o mesmo nome.");
		}
		return this.categoriaRepository.save(category);
	}
	
	public void delete(Long id) {
		Category category = this.findById(id);
		if (!category.getBooks().isEmpty()) {
			throw new ForeignKeyViolationException("Categoria possui livros cadastrados!");
		}
		this.categoriaRepository.deleteById(id);
	}

	public Boolean existsByCategory(String category) {
		return this.categoriaRepository.existsCategoryByCategory(category);
	}
	
}
