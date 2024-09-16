package com.library.service;

import com.library.dto.BookDTO;

import java.util.List;


public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO getBookByTitle(String title);
    List<BookDTO> getBooksByAuthor(String author);
    List<BookDTO> getBookByGenre(String genre);
    BookDTO addBook (BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);

}