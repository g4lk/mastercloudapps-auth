package es.codeurjc.books.services.impl;

import es.codeurjc.books.dtos.requests.BookRequestDto;
import es.codeurjc.books.dtos.responses.BookDetailsResponseDto;
import es.codeurjc.books.dtos.responses.BookResponseDto;
import es.codeurjc.books.exceptions.BookNotFoundException;
import es.codeurjc.books.models.Book;
import es.codeurjc.books.repositories.BookRepository;
import es.codeurjc.books.services.BookService;
import es.codeurjc.books.services.CommentService;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private Mapper mapper;
    private BookRepository bookRepository;
    private CommentService commentService;

    public BookServiceImpl(Mapper mapper, BookRepository bookRepository, CommentService commentService) {
        this.mapper = mapper;
        this.bookRepository = bookRepository;
        this.commentService = commentService;
    }

    @Override
    public Collection<BookResponseDto> findAll() {
        return this.bookRepository.findAll().stream()
                .map(book -> this.mapper.map(book, BookResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDetailsResponseDto save(BookRequestDto bookRequestDto) {
        Book book = this.mapper.map(bookRequestDto, Book.class);
        book = this.bookRepository.save(book);
        return this.mapper.map(book, BookDetailsResponseDto.class);
    }

    @Override
    public BookDetailsResponseDto findById(long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        return this.mapper.map(book, BookDetailsResponseDto.class);
    }

    @Override
    public Collection<BookDetailsResponseDto> findAllWithDetails() {
        return this.bookRepository.findAll().stream()
                .map(book -> this.mapper.map(book, BookDetailsResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDetailsResponseDto delete(long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        book.getComments().forEach(comment -> this.commentService.deleteComment(bookId, comment.getId()));
        bookRepository.delete(book);
        return this.mapper.map(book, BookDetailsResponseDto.class);
    }

}
