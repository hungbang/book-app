package com.evizi.sampleapplication.mapstruct.mappers;

import com.evizi.sampleapplication.mapstruct.mappers.dto.AuthorAllDto;
import com.evizi.sampleapplication.mapstruct.mappers.dto.AuthorDto;
import com.evizi.sampleapplication.mapstruct.mappers.dto.BookDto;
import com.evizi.sampleapplication.mapstruct.mappers.dto.BookSlimDto;
import com.evizi.sampleapplication.mapstruct.mappers.dto.UserGetDto;
import com.evizi.sampleapplication.mapstruct.mappers.dto.UserPostDto;
import com.evizi.sampleapplication.model.Author;
import com.evizi.sampleapplication.model.Book;
import com.evizi.sampleapplication.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    MapStructMapper INSTANCE = Mappers.getMapper( MapStructMapper.class );
    BookSlimDto bookToBookSlimDto(Book book);

    BookDto bookToBookDto(Book book);

    AuthorDto authorToAuthorDto(Author author);

    AuthorAllDto authorToAuthorAllDto(Author author);

    List<AuthorAllDto> authorsToAuthorAllDtos(List<Author> authors);

    UserGetDto userToUserGetDto(User user);

    User userPostDtoToUser(UserPostDto userPostDto);
}
