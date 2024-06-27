package dev.kiki.bookstore.controllers;

import dev.kiki.bookstore.models.Genre;
import dev.kiki.bookstore.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/genres")
public class GenreController {

    private final GenreRepository genreRepository;

    @GetMapping
    public List<Genre> genres() {
        return genreRepository.findAll();
    }

}
