package com.example.demo.service.book;

import com.example.demo.model.dto.book.request.BookRequest;
import com.example.demo.model.dto.book.response.BookListResponse;
import com.example.demo.model.dto.book.response.BookResponse;
import com.example.demo.model.entity.book.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();

    public BookService() {
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", "978-0-395-07122-1"));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", "978-0-395-19395-7"));
        books.add(new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "978-0-7475-3269-6"));
    }

    public BookListResponse getBooks() {
        return new BookListResponse(
                books.stream()
                        .map(book -> new BookResponse(new Random().nextLong(), book.getTitle(), book.getAuthor(), book.getIsbn()))
                        .toList()
        );
    }

    @Transactional
    public BookResponse createBook(final BookRequest bookRequest) {
        final var newBook = new Book(bookRequest.title(), bookRequest.author(), bookRequest.isbn());
        books.add(newBook);

        return new BookResponse(new Random().nextLong(), newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn());
    }
}
