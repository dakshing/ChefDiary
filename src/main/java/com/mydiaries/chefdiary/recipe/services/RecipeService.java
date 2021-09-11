package com.mydiaries.chefdiary.recipe.services;

import com.mydiaries.chefdiary.recipe.exceptions.RecipeNotFoundException;
import com.mydiaries.chefdiary.recipe.interfaces.RecipeDTO;
import com.mydiaries.chefdiary.recipe.interfaces.RecipeDetails;
import com.mydiaries.chefdiary.recipe.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<RecipeDetails> getRecipesForListView();

    Recipe saveRecipeDetails(RecipeDTO recipe);

    Recipe getRecipeByID(Long recipeId) throws RecipeNotFoundException;

    RecipeDTO convertRecipeToDTO(Recipe recipe);

    Recipe editRecipe(Recipe oldRecipe, RecipeDTO recipeDTO);

    void refresh(Recipe savedRecipe);

    void deleteRecipe(Long id) throws RecipeNotFoundException;
}
