package com.qa.main.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.main.domain.Book;
import com.qa.main.services.BookService;



@RestController
@CrossOrigin
@RequestMapping("/book")			// check here in case requests don't work!
public class BookController {
	
private BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	// POST REQUESTS - CREATE
		@PostMapping("/create")
		public ResponseEntity<Book> create(@RequestBody Book newBook) {
			return new ResponseEntity<Book>(service.create(newBook), HttpStatus.CREATED);			
			
		}	

		//GET REQUESTS - READ
		@GetMapping("/getAll")
		public ResponseEntity<List<Book>> getAll(){
			return new ResponseEntity<List<Book>>(this.service.getAll(), HttpStatus.OK);
		}
		
		@GetMapping("/getByID/{id}")
		public ResponseEntity<Book> getByID(@PathVariable long id) {
			return new ResponseEntity<Book>(this.service.getByID(id), HttpStatus.OK);
			
		}
		
		// PUT REQUESTS - UPDATE
		@PutMapping("/update/{id}")
		public ResponseEntity<Book> update(@PathVariable long id, @RequestBody Book newBook) {
			return new ResponseEntity<Book>(this.service.update(id, newBook), HttpStatus.OK);
		}
		
		// DELETE REQUESTS - DELETE
		@DeleteMapping ("/delete/{id}")
		public ResponseEntity<Boolean> delete(@PathVariable long id) {
			return new ResponseEntity<Boolean>(this.service.delete(id), HttpStatus.NO_CONTENT);
			
			
		}

}
