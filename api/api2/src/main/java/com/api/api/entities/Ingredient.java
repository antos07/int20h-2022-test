package com.api.api.entities;

public class Ingredient {
    private int id;
    private String name;
    private String description;
    private int category_id;//??
    private String image_url;
    private String measure;

    public Ingredient() {
    }

    public Ingredient(int id, String name, String description, int category_id, String image_url, String measure) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category_id = category_id;
        this.image_url = image_url;
        this.measure=measure;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
