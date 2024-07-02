package dev.kiki.bookstore.services;

import dev.kiki.bookstore.models.Genre;
import dev.kiki.bookstore.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> getAllGenres () {
        return genreRepository.findAll(Sort.by("name"));
    }

    public Genre createGenre(Genre genre) {
        if(genreRepository.findByNameIgnoreCase(genre.getName()).isPresent()) {
            throw new RuntimeException("Genre Already Exists");
        }
        return genreRepository.save(genre);
    }

    public void deleteGenre(Integer id) {
        if(genreRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Genre not found");
        }
        genreRepository.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        genreRepository.deleteAllById(ids);
    }

    public Genre updateGenre(Integer genreId, Genre genre) {
        Optional<Genre> existingGenreOptional = genreRepository.findById(genreId);
        if(existingGenreOptional.isEmpty()) {
            throw new RuntimeException("Genre with " + genreId + " id foes not exist");
        }
        Genre existingGenre = existingGenreOptional.get();
        existingGenre.setName(genre.getName());

        return genreRepository.save(genre);

    }


}
