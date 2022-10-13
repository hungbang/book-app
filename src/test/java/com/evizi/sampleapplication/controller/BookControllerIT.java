package com.evizi.sampleapplication.controller;

import com.evizi.sampleapplication.model.Author;
import com.evizi.sampleapplication.model.Book;
import com.evizi.sampleapplication.model.User;
import com.evizi.sampleapplication.repository.BookRepository;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void test_getById_throwUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void test_getById_returnsBook() throws Exception {
        //GIVEN
        Date date = new Date();
        String authorName = "me";
        String authorEmail = "me@evizi.com";
        Book book = new Book();
        book.setTitle("Software Testing");
        book.setReleaseDate(date);
        Author author = new Author();
        author.setName(authorName);
        author.setBirthDate(date);
        book.setAuthors(Sets.set(author));
        User user = new User();
        user.setEmail(authorEmail);
        user.setName(authorName);
        book.setUsers(Sets.set(user));
        bookRepository.save(book);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .with(SecurityMockMvcRequestPostProcessors.user("test"))
                )
                //THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Software Testing")))
                .andExpect(jsonPath("$.releaseDate", is(formatter.format(date))));
    }

}