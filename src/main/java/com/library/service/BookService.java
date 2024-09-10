package com.library.service;

import com.library.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO getBookById(Long id);
    BookDTO getBookByTitle(String title);
    List<BookDTO> getAllBooks();
    List<BookDTO> getBooksByAuthor();
    BookDTO addBook (BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    BookDTO deleteBook(Long id);

}