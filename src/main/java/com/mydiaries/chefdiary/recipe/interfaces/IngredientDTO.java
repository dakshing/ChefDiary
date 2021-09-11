package com.mydiaries.chefdiary.recipe.interfaces;

import javax.validation.constraints.NotNull;

public class IngredientDTO {
    private Long id;

    @NotNull(message = "Ingredient name is mandatory.")
    private String name;

    private long index;

    private String unit;

    @NotNull(message = "Quantity of the Ingredient is mandatory.")
    private Double quantity;

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

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
