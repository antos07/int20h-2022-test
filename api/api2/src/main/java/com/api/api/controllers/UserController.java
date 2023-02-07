package com.api.api.controllers;

import com.api.api.entities.Basket;
import com.api.api.entities.Fridge;
import com.api.api.repositories.BasketRepository;
import com.api.api.repositories.FridgeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.api.api.StaticObj.*;

@RestController
public class UserController {
    final
    FridgeRepository fridgeRepository;
    final BasketRepository basketRepository;

    public UserController(FridgeRepository fridgeRepository, BasketRepository basketRepository) {
        this.fridgeRepository = fridgeRepository;
        this.basketRepository = basketRepository;
    }

    @GetMapping("/userId")
    public int userId(){
        return user.getId();
    }
    @PostMapping("/getUserId")
    public void getUserId(@RequestBody Integer userId){

        List<Fridge> fridges=fridgeRepository.findAll();
        int index=-1;
        for(int i=0; i<fridges.size(); i++){
            if(fridges.get(i).getId()==userId) index=i;
        }
        if(index==-1){
            user.setId(userId);
            fridge.setId(userId);
            basket.setId(userId);
        }
        else{
            fridge=fridges.get(index);
            index=-1;
            List<Basket> baskets=basketRepository.findAll();
            for(int i=0; i<baskets.size(); i++){
                if(baskets.get(i).getId()==userId) baskets.get(i);
            }
        }
    }
}
