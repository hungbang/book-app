package com.evizi.sampleapplication.controller;

import com.evizi.sampleapplication.mapstruct.mappers.dto.UserGetDto;
import com.evizi.sampleapplication.mapstruct.mappers.dto.UserPostDto;
import com.evizi.sampleapplication.mapstruct.mappers.MapStructMapper;
import com.evizi.sampleapplication.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping("/users")
public class UserController {
    private final MapStructMapper mapstructMapper;

    private final UserRepository userRepository;

    public UserController(MapStructMapper mapstructMapper, UserRepository userRepository) {
        this.mapstructMapper = mapstructMapper;
        this.userRepository = userRepository;
    }


    @PostMapping
    public ResponseEntity<Void> create(
            @Valid @RequestBody UserPostDto userPostDto
    ) {
        userRepository.save(
                mapstructMapper.userPostDtoToUser(userPostDto)
        );

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getById(
            @PathVariable(value = "id") int id
    ) {
        return new ResponseEntity<>(
                mapstructMapper.userToUserGetDto(
                        userRepository.findById(id).get()
                ),
                HttpStatus.OK
        );
    }
}
