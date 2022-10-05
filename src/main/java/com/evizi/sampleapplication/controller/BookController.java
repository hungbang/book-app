package com.evizi.sampleapplication.controller;

import com.evizi.sampleapplication.mapstruct.mappers.dto.BookDto;
import com.evizi.sampleapplication.mapstruct.mappers.MapStructMapper;
import com.evizi.sampleapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final MapStructMapper mapstructMapper;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(
            MapStructMapper mapstructMapper,
            BookRepository bookRepository
    ) {
        this.mapstructMapper = mapstructMapper;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(
            @PathVariable(value = "id") int id
    ) {
        return new ResponseEntity<>(
                mapstructMapper.bookToBookDto(
                        bookRepository.findById(id).get()
                ),
                HttpStatus.OK
        );
    }

}