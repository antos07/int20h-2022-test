package com.api.api;

import java.util.ArrayList;
import java.util.List;

public class Fridge {
    List<String> ingredients=new ArrayList<String>();

    public Fridge() {
    }

    public Fridge(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
