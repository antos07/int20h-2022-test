package com.api.api;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    List<Meal> meals=new ArrayList<Meal>();
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Bucket(List<Meal> meals) {
        this.meals = meals;
    }

    public Bucket() {
    }

    public boolean isEmpty(){
        if(meals.isEmpty()) return true;
        else return false;
    }
    public void addMeal(Meal meal){
        meals.add(meal);
    }
    public class indexOutOfBounds extends Exception{
        public indexOutOfBounds(String errorMessage){
            super(errorMessage);
        }

        public indexOutOfBounds() {
            super();
        }
    }
    public void deleteMeal(@NotNull Meal meal)throws indexOutOfBounds{
        int index=0;
        while(meals.get(index).getId()!=meal.getId() && index<meals.size()) index++;
        if(index==meals.size()) throw new indexOutOfBounds("Index is out of borders");
        meals.remove(index);
    }

    public boolean isMealInBucket(Meal meal){
        if (meals.contains(meal)) return true;
        else return false;
    }

    public String allMeals(){
        return meals.toString();
    }

    public List<String> countIngredients(){
        List<String> ingredients=new ArrayList<String>();
        for(int i=0; i<meals.size(); i++){
            ingredients.add(meals.get(i).getIngredients());
        }
        return ingredients;
    }
}
