package com.api.api.entities;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
public class Fridge {
    @Id
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public class NotFound extends Exception{
        public NotFound(String errorMessage){
            super(errorMessage);
        }

        public NotFound() {
            super();
        }
    }
    public void deleteIngredient(@NotNull Ingredient ingredient)throws Fridge.NotFound {
        int index=-1;
        for(int i=0; i<ingredients.size(); i++){
            if(ingredients.get(i).getId()==ingredient.getId()) index=i;
        }
        if(index!=-1){
            ingredients.remove(index);
        }
        else{
            throw new Fridge.NotFound("Meal is not in the basket");
        }
    }
    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }


}
