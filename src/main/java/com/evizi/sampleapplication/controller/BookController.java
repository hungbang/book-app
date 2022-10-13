package com.evizi.sampleapplication.controller;

import com.evizi.sampleapplication.mapstruct.mappers.MapStructMapper;
import com.evizi.sampleapplication.mapstruct.mappers.dto.BookDto;
import com.evizi.sampleapplication.service.BookService;
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
    private final BookService bookService;
    @Autowired
    public BookController(
            MapStructMapper mapstructMapper,
            BookService bookService) {
        this.mapstructMapper = mapstructMapper;
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(
            @PathVariable(value = "id") int id
    ) {
        return new ResponseEntity<>(
                mapstructMapper.bookToBookDto(
                        bookService.findById(id)
                ),
                HttpStatus.OK
        );
    }
}