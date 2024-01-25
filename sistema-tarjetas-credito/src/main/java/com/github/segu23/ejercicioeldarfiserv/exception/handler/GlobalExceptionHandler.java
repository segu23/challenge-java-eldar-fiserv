package com.github.segu23.ejercicioeldarfiserv.exception.handler;

import com.github.segu23.ejercicioeldarfiserv.exception.CardExpiredException;
import com.github.segu23.ejercicioeldarfiserv.exception.CardNotFoundException;
import com.github.segu23.ejercicioeldarfiserv.exception.InvalidCardBrandException;
import com.github.segu23.ejercicioeldarfiserv.exception.MoneyAmountExceededException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCardBrandException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCardBrandException(InvalidCardBrandException e) {
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid card brand."));
    }

    @ExceptionHandler(CardExpiredException.class)
    public ResponseEntity<Map<String, String>> handleCardExpiredException(CardExpiredException e) {
        return ResponseEntity.badRequest().body(Map.of("error", "Card expired exception."));
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCardNotFoundException(CardNotFoundException e) {
        return ResponseEntity.status(404).body(Map.of("error", "Card not found."));
    }

    @ExceptionHandler(MoneyAmountExceededException.class)
    public ResponseEntity<Map<String, String>> handleMoneyAmountExceededException(MoneyAmountExceededException e) {
        return ResponseEntity.badRequest().body(Map.of("error", "Money amount exceed."));
    }
}
