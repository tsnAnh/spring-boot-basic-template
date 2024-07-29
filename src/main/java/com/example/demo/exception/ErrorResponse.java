package com.example.demo.exception;

import java.time.Instant;

public record ErrorResponse(Object message, int status, Instant timestamp) {
}
