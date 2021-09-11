package com.mydiaries.chefdiary.recipe.repository;

import com.mydiaries.chefdiary.recipe.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
