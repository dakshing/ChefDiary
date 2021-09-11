package com.mydiaries.chefdiary.recipe.repository;

import com.mydiaries.chefdiary.recipe.interfaces.RecipeDetails;
import com.mydiaries.chefdiary.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<RecipeDetails> findAllBy();
}
