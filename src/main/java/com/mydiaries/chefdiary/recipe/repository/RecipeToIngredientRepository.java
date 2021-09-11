package com.mydiaries.chefdiary.recipe.repository;

import com.mydiaries.chefdiary.recipe.model.RecipeIngredientPK;
import com.mydiaries.chefdiary.recipe.model.RecipeToIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeToIngredientRepository extends JpaRepository<RecipeToIngredients, RecipeIngredientPK> {
}
