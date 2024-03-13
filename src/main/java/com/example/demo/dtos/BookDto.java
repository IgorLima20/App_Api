package com.example.demo.dtos;

import java.time.LocalDate;

public class BookDto {

	private Long id;
	
	private String title;
	
	private String description;
	
	private LocalDate releaseDate;
	
	private Long category_id;

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	public Long getCategory_id() {
		return category_id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}


	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	
}
