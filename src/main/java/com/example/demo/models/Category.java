package com.example.demo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="A categoria não pode ser vazio.")
	@Size(min=3, max=40, message="A categoria precisa ter entre 3 a 40 caracteres.")
	@Column(nullable=false, length=40, unique=true)
	private String category;

	@OneToMany(mappedBy="category")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Book> books;
}
