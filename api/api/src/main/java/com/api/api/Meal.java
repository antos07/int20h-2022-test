package com.api.api;

public class Meal {
    int id;
    String name;
    String ingredients;
    String photoLink;

    public Meal(int id, String name, String ingredients, String photoLink) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.photoLink = photoLink;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
}
