package com.api.api.entities;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document
public class Basket {
    @Id
    private int id;
    private List<Meal> meals=new ArrayList<>();
    private List<Ingredient> needed_ingredients=new ArrayList<>();

    public List<Ingredient> getNeeded_ingredients() {
        return needed_ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals, Fridge fridge) {
        this.meals = meals;
        countAllNeededIngredients(fridge);
    }

    public Basket(List<Meal> meals) {
        this.meals = meals;
    }

    public Basket() {

    }

    public boolean isEmpty(){
        if(meals.isEmpty()) return true;
        else return false;
    }
    public void addMeal(Meal meal, Fridge fridge){
        meals.add(meal);
        countAllNeededIngredients(fridge);
    }
    public class NotFound extends Exception{
        public NotFound(String errorMessage){
            super(errorMessage);
        }

        public NotFound() {
            super();
        }
    }
    public void deleteMeal(@NotNull Meal meal, Fridge fridge)throws NotFound {
        int index=-1;
        for(int i=0; i<meals.size(); i++){
            if(meals.get(i).getId()==meal.getId()) index=i;
        }
        if(index!=-1){
            meals.remove(index);
            countAllNeededIngredients(fridge);
        }
        else{
            throw new NotFound("Meal is not in the basket");
        }
    }

    public boolean isMealInBucket(Meal meal){
        int index=-1;
        for(int i=0; i<meals.size(); i++){
            if(meals.get(i).getId()==meal.getId()) return true;
        }
        return false;
    }

    public String allMeals(){
        return meals.toString();
    }

    public void countAllNeededIngredients(Fridge fridge) {
        needed_ingredients.clear();
        for(int i=0; i<meals.size(); i++){
            for(int j=0; j<meals.get(i).getIngredients().size(); j++)
            if(!needed_ingredients.contains(meals.get(i).getIngredients().get(j))) needed_ingredients.add(meals.get(i).getIngredients().get(j));
        }
        for(int i=0; i<fridge.getIngredients().size(); i++){
            if (needed_ingredients.contains(fridge.getIngredients().get(i))) needed_ingredients.remove(fridge.getIngredients().get(i));
        }
    }
}
