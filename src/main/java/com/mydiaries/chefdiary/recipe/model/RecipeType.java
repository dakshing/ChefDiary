package com.mydiaries.chefdiary.recipe.model;

public enum RecipeType {
    VEG("veg"), NON_VEG("non_veg"), EGG("egg");

    private final String imagePth;

    RecipeType(String imagePth) {
        this.imagePth = imagePth;
    }

    public String getImagePth() {
        return imagePth;
    }
}
