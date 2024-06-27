package dev.kiki.bookstore.repositories;

import dev.kiki.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
