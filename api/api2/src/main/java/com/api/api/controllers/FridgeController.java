package com.api.api.controllers;

import com.api.api.StaticObj;
import com.api.api.entities.Fridge;
import com.api.api.entities.Ingredient;
import com.api.api.repositories.FridgeRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.api.api.StaticObj.fridge;

@RestController
public class FridgeController {
    final
    FridgeRepository fridgeRepository;

    public FridgeController(FridgeRepository fridgeRepository) {
        this.fridgeRepository = fridgeRepository;
    }

    @PostMapping("/deleteIngredient")
    public String deleteIngredient(@RequestBody Ingredient ingredient){
        try{
            fridge.deleteIngredient(ingredient);
            saveFridgeData();
            return "Ingredient is deleted";
        }
        catch (Fridge.NotFound ex){
            return "Ingredient can`t be deleted";
        }
    }

    @PostMapping("/addIngredient")
    public String addIngredient(@RequestBody Ingredient ingredient){
        try{
            fridge.addIngredient(ingredient);
            saveFridgeData();
            return "Ingredient is added";
        }
        catch (Exception ex){
            return "Ingredient can`t be added";
        }
    }
    public void saveFridgeData(){
        fridgeRepository.save(StaticObj.fridge);
    }
}
