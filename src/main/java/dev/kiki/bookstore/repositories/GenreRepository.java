package dev.kiki.bookstore.repositories;

import dev.kiki.bookstore.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
