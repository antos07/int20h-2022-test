package com.api.api;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private List<Meal> meals=new ArrayList<Meal>();
    private List<Ingredient> needed_ingredients=new ArrayList<Ingredient>();

    public List<Ingredient> getNeeded_ingredients() {
        return needed_ingredients;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals, Fridge fridge) {
        this.meals = meals;
        countAllNeededIngredients(fridge);
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
    public void addMeal(Meal meal, Fridge fridge){
        meals.add(meal);
        countAllNeededIngredients(fridge);
    }
    public class indexOutOfBounds extends Exception{
        public indexOutOfBounds(String errorMessage){
            super(errorMessage);
        }

        public indexOutOfBounds() {
            super();
        }
    }
    public void deleteMeal(@NotNull Meal meal, Fridge fridge)throws indexOutOfBounds{
        int index=0;
        while(meals.get(index).getId()!=meal.getId() && index<meals.size()) index++;
        if(index==meals.size()) throw new indexOutOfBounds("Index is out of borders");
        meals.remove(index);
        countAllNeededIngredients(fridge);
    }

    public boolean isMealInBucket(Meal meal){
        if (meals.contains(meal)) return true;
        else return false;
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
        /*for(int i=0; i<meals.size(); i++){
            for(int j=0; j<meals.get(i).getIngredients().size(); j++){
                if(needed_ingredients.contains(meals.get(i).getIngredients().get(j))) needed_ingredients.add(meals.get(i).getIngredients().get(j));
                else{
                    int index=needed_ingredients.indexOf(meals.get(i).getIngredients().get(j));
                    needed_ingredients.get(index).setMeasure(needed_ingredients.get(index).getMeasure()+meals.get(i).getIngredients().get(j).getMeasure());
                }
            }
        }*/
        // for(int i=0; i<fridge.getIngredients().size(); i++){
          //  if(needed_ingredients.contains(fridge.getIngredients().get(i))){

           // }
        //}
    }


}
