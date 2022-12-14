package com.qa.main.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.main.domain.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>{

}