package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images_books")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImagesBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="O caminho da imagem não pode ser vazio.")
	@Column(nullable=false, length=400)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String path;
	
	@ManyToOne
    @JoinColumn(name="book_id", nullable=false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Book book;
	
	@Transient
	private String pathName;
	
	@PostLoad
	public void pathNameLoad() {
		this.pathName = "/livro/foto/arquivo/" + this.path;
	}
	
}
