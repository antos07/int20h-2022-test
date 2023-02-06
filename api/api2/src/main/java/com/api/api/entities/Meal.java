package com.api.api.entities;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private int id;
    private String name;
    private List<Ingredient> ingredients=new ArrayList<Ingredient>();
    private String image_url;

    public Meal(int id, String name, List<Ingredient> ingredients, String photoLink) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.image_url = photoLink;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
