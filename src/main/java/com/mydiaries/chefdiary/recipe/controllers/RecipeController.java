package com.mydiaries.chefdiary.recipe.controllers;

import com.mydiaries.chefdiary.common.APIResponse;
import com.mydiaries.chefdiary.recipe.exceptions.RecipeNotFoundException;
import com.mydiaries.chefdiary.recipe.interfaces.RecipeDTO;
import com.mydiaries.chefdiary.recipe.interfaces.RecipeDetails;
import com.mydiaries.chefdiary.recipe.model.Recipe;
import com.mydiaries.chefdiary.recipe.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public ResponseEntity<?> getAllRecipes() {
        List<RecipeDetails> allRecipes = recipeService.getRecipesForListView();
        return ResponseEntity.ok(allRecipes);
    }

    @GetMapping("/recipes/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable Long id) throws RecipeNotFoundException {
        Recipe recipeDetails = recipeService.getRecipeByID(id);
        RecipeDTO recipeDTO = recipeService.convertRecipeToDTO(recipeDetails);
        return ResponseEntity.ok(recipeDTO);
    }

    @PostMapping("/recipes")
    public ResponseEntity<?> addRecipe(@RequestBody @Valid RecipeDTO recipe) {
        Recipe savedRecipe = recipeService.saveRecipeDetails(recipe);
        recipeService.refresh(savedRecipe);
        RecipeDTO savedRecipeDetails = recipeService.convertRecipeToDTO(savedRecipe);
        return ResponseEntity.ok(savedRecipeDetails);
    }

    @DeleteMapping("/recipes/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) throws RecipeNotFoundException {
        recipeService.deleteRecipe(id);
        APIResponse successResponse = new APIResponse(APIResponse.SUCCESS, "Recipe deleted successfully.");
        return ResponseEntity.ok(successResponse);
    }

    @PutMapping("/recipes/{id}")
    public ResponseEntity<?> editRecipe(@PathVariable Long id, @RequestBody RecipeDTO recipeDTO) throws RecipeNotFoundException {
        Recipe oldRecipe = recipeService.getRecipeByID(id);
        Recipe savedRecipe = recipeService.editRecipe(oldRecipe, recipeDTO);
        //recipeService.refresh(savedRecipe);
        RecipeDTO savedRecipeDTO = recipeService.convertRecipeToDTO(savedRecipe);
        return ResponseEntity.ok(savedRecipeDTO);
    }
}
