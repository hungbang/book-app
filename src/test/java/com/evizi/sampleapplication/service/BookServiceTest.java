package com.evizi.sampleapplication.service;

import com.evizi.sampleapplication.model.Author;
import com.evizi.sampleapplication.model.Book;
import com.evizi.sampleapplication.repository.BookRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookService bookService;

    @Test
    void test_createBook_success() {
        //GIVEN
        String title = "Software Testing";
        String authorName = "me";
        Date releaseDate = new Date();
        Book book = new Book();
        book.setTitle(title);
        Author author = new Author();
        author.setSurname(authorName);
        author.setName(authorName);
        book.setAuthors(Sets.set(author));
        book.setReleaseDate(releaseDate);
        Mockito.when(bookRepository.save(any())).thenReturn(book);
        //WHEN
        Book actualBook = bookService.createBook(book);
        //THEN
        assertThat(actualBook).isNotNull();
        assertThat(actualBook.getTitle()).isEqualTo(title);
        assertThat(actualBook.getAuthors()).extracting("name").containsExactly(authorName);
    }

    @Test
    void test_findById_success() {
        //GIVEN
        String title = "Software Testing";
        String authorName = "me";
        Date releaseDate = new Date();
        Book book = new Book();
        book.setTitle(title);
        Author author = new Author();
        author.setSurname(authorName);
        author.setName(authorName);
        book.setAuthors(Sets.set(author));
        book.setReleaseDate(releaseDate);
        Mockito.when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));
        //WHEN
        Book actualBook = bookService.findById(1);
        //THEN
        assertThat(actualBook).isNotNull();
        assertThat(actualBook.getTitle()).isEqualTo(title);
        assertThat(actualBook.getAuthors()).extracting("name").containsExactly(authorName);
    }
}