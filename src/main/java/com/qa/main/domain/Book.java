package com.qa.main.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	// Columns
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
//	@Column(name = "bookTitle") // name is used to change the name of the generated column.
//	@Column(unique = true) // adds the unique constraint to the column.
//	@Column(length = 50) // adds a limit to the length of the datatype.
	
	@Column(nullable = false) // adds a not null constraint to the column/ (the column can not be null)
	private String bookTitle; //Creates a column called book_title with the data type VARCHAR(255)
	
	@Column(nullable = false)
	private String authorName; //Creates a column called author_name with the data type VARCHAR(255)
	
	@Column(nullable = false)
	private String dateStarted; //Creates a column called date_started with the data type VARCHAR(255)
	
	@Column(nullable = false)
	private int bookRating; //Creates a column called book_rating with the data type INT.

	

	// Constructors
	// Default constructor (for Spring)
	
	public Book () {}
	
	
	// For creating (without ID)
	public Book(String bookTitle, String authorName, String dateStarted, int bookRating) {
		super();
		this.bookTitle = bookTitle;
		this.authorName = authorName;
		this.dateStarted = dateStarted;
		this.bookRating = bookRating;
	}
	
	// For reading (with ID)
	public Book(long id, String bookTitle, String authorName, String dateStarted, int bookRating) {
		super();
		this.id = id;
		this.bookTitle = bookTitle;
		this.authorName = authorName;
		this.dateStarted = dateStarted;
		this.bookRating = bookRating;
	}

	
	
	// Getters and Setters
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(String dateStarted) {
		this.dateStarted = dateStarted;
	}

	public int getBookRating() {
		return bookRating;
	}

	public void setBookRating(int bookRating) {
		this.bookRating = bookRating;
	}

	
	// Override Methods
	// For testing
	
	@Override
	public int hashCode() {
		return Objects.hash(authorName, bookRating, bookTitle, dateStarted, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authorName, other.authorName) && bookRating == other.bookRating
				&& Objects.equals(bookTitle, other.bookTitle) && Objects.equals(dateStarted, other.dateStarted)
				&& id == other.id;
	}

	
}
