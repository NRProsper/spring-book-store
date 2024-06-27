package dev.kiki.bookstore;

import com.github.javafaker.Faker;
import dev.kiki.bookstore.models.Book;
import dev.kiki.bookstore.models.Genre;
import dev.kiki.bookstore.repositories.BookRepository;
import dev.kiki.bookstore.repositories.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            GenreRepository genreRepository,
            BookRepository bookRepository
            ) {
        return args -> {
            Faker faker = new Faker();
            for (int i = 0; i < 5; i++) {
                Book book = new Book();
                book.setTitle(faker.book().title());
                book.setDescription(faker.lorem().sentence());
                book.setISBN(faker.lorem().characters(12, true));
                List<Genre> genres = new ArrayList<>();
                for(int j = 0; j < 2; j++) {
                    var genre = new Genre();
                    genre.setName(faker.book().genre());
                    genres.add(genre);
                }
                book.setGenres(genres);
                bookRepository.save(book);
                genreRepository.saveAll(genres);
            }
        };
    }


}
