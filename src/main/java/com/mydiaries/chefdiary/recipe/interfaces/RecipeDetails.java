package com.mydiaries.chefdiary.recipe.interfaces;

import com.mydiaries.chefdiary.recipe.model.RecipeType;

public interface RecipeDetails {
    Long getId();
    String getName();
    String getDescription();
    String getBaseIngredient();
    RecipeType getType();
}
