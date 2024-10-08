package com.library.repository;

import com.library.entity.Book;
import com.library.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {
    Optional<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(Genre genre);
}
