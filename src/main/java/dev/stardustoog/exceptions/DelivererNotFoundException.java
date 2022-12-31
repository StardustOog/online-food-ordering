package dev.stardustoog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "deliverer not found")
public class DelivererNotFoundException extends RuntimeException{
}
