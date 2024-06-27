package dev.kiki.bookstore.services;

import dev.kiki.bookstore.cloudinary.CloudinaryService;
import dev.kiki.bookstore.models.Book;
import dev.kiki.bookstore.models.Genre;
import dev.kiki.bookstore.repositories.BookRepository;
import dev.kiki.bookstore.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CloudinaryService cloudinaryService;
    private final GenreRepository genreRepository;

    public Book createBook(
            String title,
            String ISBN,
            List<Integer> genresIds,
            String description,
            MultipartFile img,
            MultipartFile pdf
            ) throws IOException {

        String imgUrl = cloudinaryService.uploadFile(img, "BookStore/img");
        String pdfUrl = cloudinaryService.uploadFile(pdf, "BookStore/pdf");

        List<Genre> genres = genreRepository.findAllById(genresIds);

        Book book = new Book();
        book.setTitle(title);
        book.setISBN(ISBN);
        book.setGenres(genres);
        book.setDescription(description);
        book.setCoverImgUrl(imgUrl);
        book.setPdfUrl(pdfUrl);

        return bookRepository.save(book);

    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
