package com.evizi.sampleapplication.controller;

import com.evizi.sampleapplication.mapstruct.mappers.MapStructMapper;
import com.evizi.sampleapplication.mapstruct.mappers.dto.AuthorAllDto;
import com.evizi.sampleapplication.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final MapStructMapper mapstructMapper;

    private final AuthorService authorService;

    @Autowired
    public AuthorController(
            MapStructMapper mapstructMapper,
            AuthorService authorService
    ) {
        this.mapstructMapper = mapstructMapper;
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<AuthorAllDto>> getAll() {
        return new ResponseEntity<>(
                mapstructMapper.authorsToAuthorAllDtos(
                        authorService.findAll()
                ),
                HttpStatus.OK
        );
    }

}