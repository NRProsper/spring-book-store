package dev.kiki.bookstore;

import com.github.javafaker.Faker;
import dev.kiki.bookstore.models.Book;
import dev.kiki.bookstore.models.Genre;
import dev.kiki.bookstore.models.Role;
import dev.kiki.bookstore.models.User;
import dev.kiki.bookstore.repositories.BookRepository;
import dev.kiki.bookstore.repositories.GenreRepository;
import dev.kiki.bookstore.repositories.RoleRepository;
import dev.kiki.bookstore.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
