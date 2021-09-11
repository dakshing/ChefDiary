package com.mydiaries.chefdiary.recipe.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "default_unit", length = 10)
    private String defaultUnit;

    @OneToMany(mappedBy = "ingredient")
    private Set<RecipeToIngredients> recipes;

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

    public String getDefaultUnit() {
        return defaultUnit;
    }

    public void setDefaultUnit(String defaultUnit) {
        this.defaultUnit = defaultUnit;
    }

    public Set<RecipeToIngredients> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<RecipeToIngredients> recipes) {
        this.recipes = recipes;
    }
}
