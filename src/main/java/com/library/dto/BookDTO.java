package com.library.dto;
import com.library.entity.Genre;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class BookDTO {
    private Long id;
    @NotNull (message = "(˘ŏ_ŏ)  \nAuthor field cannot be empty.")
    @Size (min = 1, max = 100, message = "(˶˃ᆺ˂˶) \nAuthor must have between 1 and 100 characters.")
    private String author;
    @NotNull (message = "(˘ŏ_ŏ) \nTitle field cannot be empty.")
    @Size (min = 1, max = 100, message = "(˶˃ᆺ˂˶) \nTitle must have between 1 and 100 characters.")
    private String title;
    @NotNull(message = "(｡•́︿•̀｡) \nPlease,choose a genre.")
    private Genre genre;
    @NotNull (message = "( ˶°ㅁ°) !!\nPrice field cannot be empty")
    private double price;
    private LocalDate releaseDate;

    public BookDTO(){}
    public BookDTO(String author, String title, Genre genre, double price, LocalDate releaseDate){
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.releaseDate =releaseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
