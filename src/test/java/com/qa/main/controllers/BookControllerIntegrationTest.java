package com.qa.main.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.main.domain.Book;


@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testschema.sql", "classpath:testdata.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test") // Switching to H2, for the test
public class BookControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper; // Used for converting objects to JSON
	
	@Test
	public void createTest() throws Exception {
		
		
		// Create an object for posting
				Book entry = new Book("Book", "Author", "02/02/2000", 10);
				String entryAsJSON = mapper.writeValueAsString(entry);
				
				// Create an object for checking the result
				Book result = new Book(2L, "Book", "Author", "02/02/2000", 10);
				String resultAsJSON = mapper.writeValueAsString(result);
				
				mvc.perform(post("/book/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(entryAsJSON))
						.andExpect(content().json(resultAsJSON));
	}
	


	@Test
	public void readAllTest() throws Exception {
		// Create a list to check the output of readAll
		List<Book> result = new ArrayList<>();
		// Add the single entry to the list
		result.add(new Book(1L, "example book", "example author", "01/01/2000", 10));
		// Convert the list to JSON (The API responds in JSON)
		String resultAsJSON = mapper.writeValueAsString(result);
		
		mvc.perform(get("/book/getAll")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(resultAsJSON));
	}
	
	@Test
	public void readByIdTest() throws Exception {
		
		Book A = new Book(1L, "example book", "example author", "01/01/2000", 10);
		String BookJSON = mapper.writeValueAsString(A);
		mvc.perform(get("/book/getByID/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(BookJSON));
	}
	
	@Test
	public void updateTest() throws Exception {
		
		Book update = new Book(1L, "example book", "example author", "01/01/2000", 10);
		String updateJSON = mapper.writeValueAsString(update);

		Book expected = new Book(1L, "example book", "example author", "01/01/2000", 10);
		String expectedJSON = mapper.writeValueAsString(expected);

		mvc.perform(put("/book/update/1").contentType(MediaType.APPLICATION_JSON).content(updateJSON))
				.andExpect(content().json(expectedJSON));
	}
	
	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete("/book/delete/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
}
