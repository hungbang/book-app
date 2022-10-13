package com.evizi.sampleapplication.service;

import com.evizi.sampleapplication.model.Book;
import com.evizi.sampleapplication.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }
}
