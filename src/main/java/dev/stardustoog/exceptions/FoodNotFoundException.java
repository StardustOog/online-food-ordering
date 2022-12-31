package dev.stardustoog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "food not found")
public class FoodNotFoundException extends RuntimeException{
}
