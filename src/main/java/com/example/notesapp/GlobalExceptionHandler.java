package com.example.notesapp;



import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception e) {
        // 🔥 LOG THE REAL ERROR — THIS IS WHAT YOU’VE BEEN MISSING
        System.err.println("🔥 SERVER ERROR: " + e.getMessage());
        e.printStackTrace();

        // Return 500 with error message so browser doesn’t show fake CORS error
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Server Error: " + e.getMessage());
    }
}
