package com.example.api2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ApiApplication {
    User user=new User();
    Basket basket =new Basket();
    Fridge fridge=new Fridge();
    @Autowired
    FridgeRepository fridgeRepository;

    @Autowired
    BasketRepository basketRepository;
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);

    }
    @GetMapping("/userid")
    public int userId(){
        return user.getId();
    }

    @GetMapping("/allMeals")
    public String allMeals(){
        return basket.allMeals();
    }
    @GetMapping("/isBasketEmpty")
    public boolean isBasketEmpty(){
      return basket.isEmpty();
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
        fridge.setIngredients(List.of(milk, apple));
        Meal pasta=new Meal(1, "pasta", List.of(rise, milk, water), "kkkk");
        basket.setMeals(List.of(pasta), fridge);
        idSetting();
        dataSaving();
    }
    @GetMapping("/ingr")
    public List<Ingredient> neededIng(){
        return basket.getNeeded_ingredients();
    }

    public void idSetting(){
        fridge.setId(user.getId());
        basket.setId(user.getId());
    }

    public void dataSaving(){
        fridgeRepository.save(fridge);
        basketRepository.save(basket);
    }
}
