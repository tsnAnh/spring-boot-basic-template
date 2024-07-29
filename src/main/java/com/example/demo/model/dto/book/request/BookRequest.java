package com.example.demo.model.dto.book.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record BookRequest(@NotEmpty String title, @NotNull String author, String isbn) {
}
