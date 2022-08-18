package com.qa.main.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.main.domain.Book;
import com.qa.main.repos.BookRepo;

@Service
public class BookService {

	private BookRepo repo;
	
	public BookService(BookRepo repo){
		this.repo = repo;
		
	}
	
	public Book create(Book newBook) {
		return repo.saveAndFlush(newBook);
	}
	
	public List<Book> getAll() {
		return repo.findAll();
	}
	
	public Book getByID(long id) {
		return repo.findById(id).get();
	}
	
	public Book update(long id, Book newBook) {
		// we get the existing entry
		Book existing = repo.findById(id).get();
		
		//Update the existing entry, to match the incoming object
		existing.setBookTitle(newBook.getBookTitle());
		existing.setAuthorName(newBook.getAuthorName());
		existing.setDateStarted(newBook.getDateStarted());
		existing.setBookRating(newBook.getBookRating());
		
		
		// Save the updated entry back into the DB (ID is the same)
		return repo.saveAndFlush(existing);
		
	}
	
	public boolean delete(long id) {
		repo.deleteById(id);
		
		return !repo.existsById(id);
	}
}
