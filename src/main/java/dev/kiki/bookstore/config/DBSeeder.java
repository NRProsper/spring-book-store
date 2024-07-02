package dev.kiki.bookstore.config;

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
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DBSeeder {

    @Bean
    CommandLineRunner commandLineRunner(
            GenreRepository genreRepository,
            BookRepository bookRepository,
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
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
            if(roleRepository.findAll().isEmpty()) {
                Role adminRole = new Role();
                Role userRole = new Role();

                adminRole.setRole("ADMIN");
                userRole.setRole("USER");

                roleRepository.save(adminRole);
                roleRepository.save(userRole);
            }

            Optional<Role> adminRoleOpt = roleRepository.findByRoleIgnoreCase("ADMIN");
            if (adminRoleOpt.isPresent()) {
                Role adminRole = adminRoleOpt.get();
                User adminUser = User.builder()
                        .firstName("Admin")
                        .lastName("User")
                        .email("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(adminRole)
                        .createAt(LocalDateTime.now())
                        .build();
                userRepository.save(adminUser);
            }
        };
    }


}
