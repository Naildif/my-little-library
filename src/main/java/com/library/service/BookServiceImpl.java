package com.library.service;

import com.library.dto.BookDTO;
import com.library.entity.Book;
import com.library.entity.Genre;
import com.library.exception.BookNotFoundException;
import com.library.exception.TitleNotFoundException;
import com.library.exception.AuthorNotFoundException;
import com.library.exception.GenreNotFoundException;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::convertEntityToDTO)
        .orElseThrow(()-> new BookNotFoundException ("Book with Id " + id
                + " not found \n૮ ◞ ﻌ ◟ ა"));
    }

    @Override
    public BookDTO getBookByTitle(String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        return book.map(this::convertEntityToDTO)
                .orElseThrow(()-> new TitleNotFoundException ("Book with the title " + title
                        + " was not found \n૮ ◞ ﻌ ◟ ა"));
    }
    @Override
    public List<BookDTO> getBooksByAuthor(String author) {
        List<Book> bookList = bookRepository.findByAuthor(author);
        if (bookList.isEmpty()) {
            throw new AuthorNotFoundException("The author " + author
                    + " was not found \n૮ ◞ ﻌ ◟ ა");
        }
        return bookList.stream().map(this::convertEntityToDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getBookByGenre(String genre) {
        Genre bookGenre;
        try {
            bookGenre = Genre.valueOf(genre.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new GenreNotFoundException("The genre " + genre
                    + " does not exist \n૮ ◞ ﻌ ◟ ა");
        }
        List<Book> booksByGenre = bookRepository.findByGenre(bookGenre);
        if (booksByGenre.isEmpty()) {
            throw new GenreNotFoundException("No books found for the genre " + genre
                    +"\n૮꒰◞ ˕ ◟ ྀི꒱ა");
        }
        return booksByGenre.stream().map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = convertDTOToEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return convertEntityToDTO(savedBook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException ("Book with Id " + id
                        + " not found \n૮ ◞ ﻌ ◟ ა"));
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setGenre(bookDTO.getGenre());
        existingBook.setPrice(bookDTO.getPrice());
        existingBook.setReleaseDate(bookDTO.getReleaseDate());
        Book updatedBook = bookRepository.save(existingBook);
        return convertEntityToDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException ("Book with Id " + id
                        + " not found \n૮ ◞ ﻌ ◟ ა"));
        bookRepository.delete(book);
    }

    public BookDTO convertEntityToDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setReleaseDate(book.getReleaseDate());
        return bookDTO;
    }
    public Book convertDTOToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setAuthor(bookDTO.getAuthor());
        book.setTitle(bookDTO.getTitle());
        book.setGenre(bookDTO.getGenre());
        book.setPrice(bookDTO.getPrice());
        book.setReleaseDate(bookDTO.getReleaseDate());
        return book;
    }
}
