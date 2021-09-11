package com.mydiaries.chefdiary.recipe.controllers;

import com.mydiaries.chefdiary.common.APIResponse;
import com.mydiaries.chefdiary.recipe.exceptions.InvalidInputException;
import com.mydiaries.chefdiary.recipe.exceptions.RecipeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class RecipeExceptionHandler {
    @ExceptionHandler(value = {RecipeNotFoundException.class, InvalidInputException.class})
    public ResponseEntity<?> recipeNotFound(RecipeNotFoundException exception) {
        String message = Optional.ofNullable(exception.getMessage()).orElse("Recipe with given ID is not found.");
        APIResponse apiResponse = new APIResponse(APIResponse.FAILED, message);
        return ResponseEntity.badRequest().body(apiResponse);
    }
}
