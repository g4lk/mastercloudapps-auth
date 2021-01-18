package es.codeurjc.books.services;

import es.codeurjc.books.dtos.requests.BookRequestDto;
import es.codeurjc.books.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.books.dtos.responses.BookResponseDto;

import java.util.Collection;

public interface BookService {

    Collection<BookResponseDto> findAll();

    BookDetailsResponseDto save(BookRequestDto bookRequestDto);

    BookDetailsResponseDto findById(long bookId);

    Collection<BookDetailsResponseDto> findAllWithDetails();

    BookDetailsResponseDto delete(long bookId);

}
