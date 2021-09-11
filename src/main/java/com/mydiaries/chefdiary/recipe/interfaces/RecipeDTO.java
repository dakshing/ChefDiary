package com.mydiaries.chefdiary.recipe.interfaces;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class RecipeDTO {
    private Long id;

    @NotNull
    private String name;

    private String description;

    private String baseIngredient;

    private String type;

    Set<@Valid IngredientDTO> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaseIngredient() {
        return baseIngredient;
    }

    public void setBaseIngredient(String baseIngredient) {
        this.baseIngredient = baseIngredient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
}
