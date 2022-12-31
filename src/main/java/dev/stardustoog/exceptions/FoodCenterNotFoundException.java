package dev.stardustoog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Such food center doesn't exist")
public class FoodCenterNotFoundException extends RuntimeException{
}
