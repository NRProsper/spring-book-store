package dev.kiki.bookstore.controllers;

import dev.kiki.bookstore.common.ApiResponse;
import dev.kiki.bookstore.models.Book;
import dev.kiki.bookstore.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(
            @RequestParam("title") String title,
            @RequestParam("ISBN") String ISBN,
            @RequestParam("genres") List<Integer> genreIds,
            @RequestParam("description") String description,
            @RequestParam("img") MultipartFile img,
            @RequestParam("pdf") MultipartFile pdf
            ) throws IOException {


        var book = bookService.createBook(title, ISBN, genreIds, description, img, pdf);

        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Book>>> getAllBooks() {
        var books = bookService.getAllBooks();
        ApiResponse<List<Book>> response = new ApiResponse<>(
                books,
                "All Books",
                HttpStatus.OK.value()
        );
        return ResponseEntity.ok(response);
    }

}
