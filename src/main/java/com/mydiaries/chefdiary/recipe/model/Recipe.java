package com.mydiaries.chefdiary.recipe.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    private String description;

    @Column(name = "base_ingredient", length = 100)
    private String baseIngredient;

    @Column(name = "type", length = 10)
    @Enumerated(EnumType.STRING)
    private RecipeType type;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeToIngredients> ingredients;

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

    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    public Set<RecipeToIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<RecipeToIngredients> ingredients) {
        this.ingredients = ingredients;
    }
}
