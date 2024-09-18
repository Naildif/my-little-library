package com.library.controller;

import com.library.dto.BookDTO;
import com.library.entity.Genre;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        if (bookDTO != null) {
            return ResponseEntity.ok(bookDTO);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/title/{title}")
    public ResponseEntity <BookDTO> getBookByTitle(@PathVariable String title) {
        BookDTO bookTitle = bookService.getBookByTitle(title);
        if (bookTitle != null) {
            return ResponseEntity.ok(bookTitle);
        } else {

            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity <List<BookDTO>> getAllBooks(){
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable String author){
        List<BookDTO> booksByAuthor = bookService.getBooksByAuthor(author);
        if (booksByAuthor != null && !booksByAuthor.isEmpty()){
            return ResponseEntity.ok(booksByAuthor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(@PathVariable Genre genre){
        List<BookDTO> booksByGenre = bookService.getBooksByGenre(genre);
        if (booksByGenre != null && !booksByGenre.isEmpty()){
            return ResponseEntity.ok(booksByGenre);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO){
        BookDTO addedBook = bookService.addBook(bookDTO);
        URI location = URI.create(String.format("/book/%d", addedBook.getId()));
        return ResponseEntity.created(location).body(addedBook);
    }
    @PutMapping ("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        if(updatedBook != null){
            return ResponseEntity.ok(updatedBook);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping
   public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        BookDTO deletedBook = bookService.getBookById(id);
        if (deletedBook != null){
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
