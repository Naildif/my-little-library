package com.library.service;

import com.library.dto.BookDTO;
import com.library.entity.Genre;

import java.util.List;


public interface BookService {
    BookDTO getBookById(Long id);
    BookDTO getBookByTitle(String title);
    List<BookDTO> getAllBooks();
    List<BookDTO> getBooksByAuthor(String author);
    List<BookDTO> getBooksByGenre(Genre genre);
    BookDTO addBook (BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);

}