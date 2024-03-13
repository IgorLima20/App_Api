package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Book;
import com.example.demo.models.ImagesBook;
import com.example.demo.repositories.ImagesBookRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class ImagesBookService {
	
	@Autowired
	private ImagesBookRepository imagesBookRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private FileService fileService;
	
	private String directoryImage = "/imagens-book/";
	
	public ImagesBook findById(Long id) {
		Optional<ImagesBook> imagesBook = this.imagesBookRepository.findById(id);
		return imagesBook.orElseThrow(() -> new ObjectNotFoundException("Imagem de livro informada não encontrada.")); 
	}
	
	public List<ImagesBook> findByBook(Long idBook) {
		Book book = this.bookService.findById(idBook);
		return this.imagesBookRepository.findByBook(book);
	}
	
	public Resource getImage(String fileName) {
		return this.fileService.getFileByName(this.directoryImage, fileName);
	}
	
	public void create(Long idBook, MultipartFile file) {
		Book book = this.bookService.findById(idBook);
		String fileName = this.fileService.saveFile(this.directoryImage, file);
		ImagesBook imagesBook = new ImagesBook();
		imagesBook.setPath(fileName);
		imagesBook.setBook(book);
		this.imagesBookRepository.save(imagesBook);
	}
	
	public void delete(Long id) {
		ImagesBook imageBook = this.findById(id);
		this.fileService.deleteFile(this.directoryImage, imageBook.getPath());
		this.imagesBookRepository.delete(imageBook);
	}
	
}
