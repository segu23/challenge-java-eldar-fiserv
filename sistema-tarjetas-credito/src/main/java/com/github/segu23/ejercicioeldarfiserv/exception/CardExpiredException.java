package com.github.segu23.ejercicioeldarfiserv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CardExpiredException extends Exception {
}
