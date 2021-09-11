package com.mydiaries.chefdiary.recipe.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RecipeIngredientPK implements Serializable {
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "ingredient_id")
    private Long ingredientId;

    public RecipeIngredientPK() {
    }

    public RecipeIngredientPK(Long recipeId, Long ingredientId) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RecipeIngredientPK that = (RecipeIngredientPK) obj;
        return Objects.equals(getRecipeId(), that.getRecipeId()) && Objects.equals(getIngredientId(), that.getIngredientId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRecipeId(), getIngredientId());
    }
}
