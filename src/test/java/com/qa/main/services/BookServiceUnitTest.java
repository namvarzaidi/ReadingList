package com.qa.main.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.main.domain.Book;
import com.qa.main.repos.BookRepo;



@SpringBootTest
public class BookServiceUnitTest {
	@Autowired
	private BookService service;

	@MockBean
	private BookRepo repo;

	@Test
	public void testCreate() {
		// Create and object for saving
		Book entry = new Book("Book", "Author", "02/02/2000", 10);

		// Create an object for the result
		Book result = new Book(10L,"Book", "Author", "02/02/2000", 10);

		Mockito.when(repo.saveAndFlush(entry)).thenReturn(result);

		assertEquals(result, service.create(entry));
	}

	@Test
	public void testGetAll() {
		// Create and object for saving
		List<Book> result = new ArrayList<>();
		result.add(new Book("Book", "Author", "02/02/2000", 10));

		Mockito.when(repo.findAll()).thenReturn(result);

		assertEquals(result, service.getAll());
	}

	@Test
	public void getByIdTest() {

		long id = 1;
		// Create an object for saving
		Book result = new Book(1L,"Book", "Author", "02/02/2000", 10);
		Optional<Book> resultI = Optional.of(result);
		Mockito.when(repo.findById(id)).thenReturn(resultI);

		assertEquals(result, service.getByID(id));

	}

	
	@Test
	
	public void updateTest() {
		long id= 1L;
		Book update  = new Book (1L, "Book", "Author", "02/02/2000", 10);
		Optional <Book> updateOp = Optional.ofNullable(update);
		Book expected = new Book (1L,"Book", "Author", "02/02/2000", 10);
		
		
		Mockito.when(repo.findById(id)).thenReturn(updateOp);
		Mockito.when(repo.saveAndFlush(update)).thenReturn(expected);
		
		
		assertEquals(expected,service.update(id, update));
	}
	
	@Test
	
	public void deleteTest() {
		
		Long id = 1L;
		Mockito.when(repo.existsById(1L)).thenReturn(false);
		
		assertEquals(true, service.delete(id));
		
		
	}
	
}