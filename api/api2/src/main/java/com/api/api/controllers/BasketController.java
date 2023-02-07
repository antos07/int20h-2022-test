package com.api.api.controllers;

import com.api.api.entities.Basket;
import com.api.api.entities.Ingredient;
import com.api.api.entities.Meal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.api.api.StaticObj.basket;
import static com.api.api.StaticObj.fridge;

@RestController
public class BasketController {

    @GetMapping("/isBasketEmpty")
    public boolean isBasketEmpty(){
        return basket.isEmpty();
    }
    @GetMapping("/getMeals")
    public List<Meal> getMeals(){
        return basket.getMeals();
    }
    @GetMapping("/ingredients")
    public List<Ingredient> neededIng(){
        return basket.getNeeded_ingredients();
    }
    @PostMapping("/addMeal")
    public String addMeal(@RequestBody Meal meal){
        try{
            basket.addMeal(meal, fridge);
            return "Meal is added";
        }
        catch (Exception ex){
            return "Meal can`t be added";
        }
    }
    @GetMapping("/allMeals")
    public String allMeals(){
        return basket.allMeals();
    }

    @PostMapping("/deleteMeal")
    public String deleteMeal(@RequestBody Meal meal){
        try{
            basket.deleteMeal(meal, fridge);
            return "Meal is deleted";
        }
        catch (Basket.NotFound ex){
            return "Meal can`t be deleted";
        }
    }
}
