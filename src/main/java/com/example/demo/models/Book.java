package com.example.demo.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Data
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="O título não pode ser vazio.")
	@Size(min=1, max=100, message="O título precisa ter entre 1 a 100 caracteres.")
	@Column(nullable=false, length=100)
	private String title;
	
	@NotBlank(message="A descrição não pode ser vazio.")
	@Size(min=1, max=800, message="A descrição precisa ter entre 1 a 800 caracteres.")
	@Column(nullable=false, length=800)
	private String description;
	
	@NotNull(message="A data de lançamento não pode ser vazio.")
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate releaseDate;
	
	@ManyToOne
    @JoinColumn(name="category_id", nullable=false)
	private Category category;
	
	@OneToMany(mappedBy="book")
	private List<ImagesBook> imagesBook;

	public Book(Long id, String title, String description, LocalDate releaseDate, Category category) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.category = category;
	}
	
	
	
}
