package com.evizi.sampleapplication.service;

import com.evizi.sampleapplication.model.Author;
import com.evizi.sampleapplication.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
