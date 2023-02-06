package com.example.api2;

import java.util.ArrayList;
import java.util.List;

public class Fridge {
    private List<Ingredient> ingredients=new ArrayList<Ingredient>();

    public Fridge() {
    }

    public Fridge(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
