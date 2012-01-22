package com.fundynamic.webstore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fundynamic.webstore.domain.Book;

@Service
public class BookServiceStub implements BookService {

	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book("Clean Code", "Robert C. Martin"));
		books.add(new Book("The Clean Coder", "Robert C. Martin"));
		return books;
	}

}
