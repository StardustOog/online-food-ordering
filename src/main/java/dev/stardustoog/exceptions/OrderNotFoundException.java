package dev.stardustoog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "order not found")
public class OrderNotFoundException extends RuntimeException {
}
