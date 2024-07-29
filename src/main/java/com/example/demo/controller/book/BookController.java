package com.example.demo.controller.book;

import com.example.demo.model.dto.book.request.BookRequest;
import com.example.demo.model.dto.book.response.BookListResponse;
import com.example.demo.model.dto.book.response.BookResponse;
import com.example.demo.service.book.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BookListResponse> getBooks() {
        return ResponseEntity.ok().body(bookService.getBooks());
    }

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok().body(bookService.createBook(bookRequest));
    }
}
