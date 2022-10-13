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
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MapStructMapperTest {

    @Test
    void test_bookToBookSlimDto_returnCorrectDto() {
        //GIVEN
        String title = "Software Testing";
        Date releaseDate = new Date();
        Book book = new Book();
        book.setTitle(title);
        book.setReleaseDate(releaseDate);
        //WHEN
        BookSlimDto bookSlimDto = MapStructMapper.INSTANCE.bookToBookSlimDto(book);
        //THEN
        assertThat(bookSlimDto).isNotNull();
        assertThat(bookSlimDto.getTitle()).isEqualTo(title);
    }

    @Test
    void test_bookToBookDto_returnCorrectDto() {
        //GIVEN
        String title = "Software Testing";
        Date releaseDate = new Date();
        Book book = new Book();
        book.setTitle(title);
        book.setReleaseDate(releaseDate);
        //WHEN
        BookDto bookDto = MapStructMapper.INSTANCE.bookToBookDto(book);
        //THEN
        assertThat(bookDto).isNotNull();
        assertThat(bookDto.getTitle()).isEqualTo(title);
        assertThat(bookDto.getReleaseDate()).isEqualTo(releaseDate);
    }

    @Test
    void test_authorToAuthorDto_returnDto() {
        //GIVEN
        String surname = "Martin";
        String name = "Robert";
        Date birthDate = new Date();
        Author author = new Author();
        author.setSurname(surname);
        author.setName(name);
        author.setBirthDate(birthDate);
        //WHEN
        AuthorDto authorDto = MapStructMapper.INSTANCE.authorToAuthorDto(author);
        //THEN
        assertThat(authorDto).isNotNull();
        assertThat(authorDto.getSurname()).isEqualTo(surname);
        assertThat(authorDto.getName()).isEqualTo(name);
        assertThat(authorDto.getBirthDate()).isEqualTo(birthDate);
    }

    @Test
    void test_authorToAuthorAllDto_returnDto() {
        //GIVEN
        String surname = "Martin";
        String name = "Robert";
        Date birthDate = new Date();
        Author author = new Author();
        author.setSurname(surname);
        author.setName(name);
        author.setBirthDate(birthDate);
        //WHEN
        AuthorAllDto authorDto = MapStructMapper.INSTANCE.authorToAuthorAllDto(author);
        //THEN
        assertThat(authorDto).isNotNull();
        assertThat(authorDto.getSurname()).isEqualTo(surname);
        assertThat(authorDto.getName()).isEqualTo(name);
        assertThat(authorDto.getBirthDate()).isEqualTo(birthDate);
    }

    @Test
    void test_authorsToAuthorAllDtos_returnListOfDto() {
        //GIVEN
        String surname = "Martin";
        String name = "Robert";
        Date birthDate = new Date();

        String surname1 = "Martin";
        String name1 = "Robert";
        Date birthDate1 = new Date();

        Author author = new Author();
        author.setSurname(surname);
        author.setName(name);
        author.setBirthDate(birthDate);
        Author author1 = new Author();
        author.setSurname(surname1);
        author.setName(name1);
        author.setBirthDate(birthDate1);
        //WHEN
        List<AuthorAllDto> authorDtos = MapStructMapper.INSTANCE.authorsToAuthorAllDtos(Lists.list(author, author1));
        //THEN
        assertThat(authorDtos).isNotEmpty();
        assertThat(authorDtos).extracting("surname").contains(surname, surname1);
        assertThat(authorDtos).extracting("name").contains(name, name1);
        assertThat(authorDtos).extracting("birthDate").contains(birthDate, birthDate1);
    }

    @Test
    void test_userToUserGetDto_returnDto() {
        //GIVEN
        String surname = "Martin";
        String name = "Robert";
        String email = "martin.robert@evizi.com";
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        //WHEN
        UserGetDto userGetDto = MapStructMapper.INSTANCE.userToUserGetDto(user);
        //THEN
        assertThat(userGetDto).isNotNull();
        assertThat(userGetDto.getSurname()).isEqualTo(surname);
        assertThat(userGetDto.getName()).isEqualTo(name);
        assertThat(userGetDto.getEmail()).isEqualTo(email);
    }

    @Test
    void test_userPostDtoToUser_returnUser() {
        //GIVEN
        String surname = "Martin";
        String name = "Robert";
        String email = "martin.robert@evizi.com";
        UserPostDto userPostDto = new UserPostDto();
        userPostDto.setName(name);
        userPostDto.setSurname(surname);
        userPostDto.setEmail(email);
        //WHEN
        User user = MapStructMapper.INSTANCE.userPostDtoToUser(userPostDto);
        //THEN
        assertThat(user).isNotNull();
        assertThat(user.getSurname()).isEqualTo(surname);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
    }
}