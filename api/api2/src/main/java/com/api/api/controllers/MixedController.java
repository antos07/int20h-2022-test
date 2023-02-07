package com.api.api.controllers;

import com.api.api.StaticObj;
import com.api.api.entities.Meal;
import com.api.api.entities.Ingredient;
import com.api.api.repositories.BasketRepository;
import com.api.api.repositories.FridgeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class MixedController {

    final
    FridgeRepository fridgeRepository;

    final
    BasketRepository basketRepository;

    public MixedController(BasketRepository basketRepository, FridgeRepository fridgeRepository) {
        this.basketRepository = basketRepository;
        this.fridgeRepository = fridgeRepository;
    }

    @GetMapping("/dummy")
    public void dummyInfo(){
        //bucket.setMeals();
        Ingredient milk=new Ingredient(1, "jj", "jjj", 89, "hjk");
        Ingredient rise=new Ingredient(2, "jj", "jjj", 89, "hjk");
        Ingredient lemon=new Ingredient(3, "jj", "jjj", 89, "hjk");
        Ingredient apple=new Ingredient(4, "jj", "jjj", 89, "hjk");
        Ingredient sugar=new Ingredient(5, "jj", "jjj", 89, "hjk");
        Ingredient salt=new Ingredient(6, "jj", "jjj", 89, "hjk");
        Ingredient water=new Ingredient(7, "jj", "jjj", 89, "hjk");
        Ingredient peach=new Ingredient(8, "jj", "jjj", 89, "hjk");
        Ingredient mint=new Ingredient(9, "jj", "jjj", 89, "hjk");
        Ingredient bacon=new Ingredient(10, "jj", "jjj", 89, "hjk");
        StaticObj.fridge.setIngredients(List.of(milk, apple));
        Meal pasta=new Meal(1, "pasta", List.of(rise, milk, water), "kkkk");
        Meal pasta2=new Meal(11, "pasta", List.of(rise, milk, water), "kkkk");
        StaticObj.basket.setMeals(new ArrayList<>(Arrays.asList(pasta)), StaticObj.fridge);
        StaticObj.basket.addMeal(pasta2, StaticObj.fridge);
        idSetting();
        dataSaving();
    };
    @GetMapping("/save")
    public void save(){
        idSetting();
        dataSaving();
    }

    public void idSetting(){
        StaticObj.fridge.setId(StaticObj.user.getId());
        StaticObj.basket.setId(StaticObj.user.getId());
    }
    public void dataSaving(){
        fridgeRepository.save(StaticObj.fridge);
        basketRepository.save(StaticObj.basket);
    }
}
