package com.api.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiApplication {
    User user=new User();
    Bucket bucket=new Bucket();
    Fridge fridge=new Fridge();
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
    @GetMapping("/userid")
    public int userId(){
        return user.getId();
    }

    @GetMapping("/allMeals")
    public String allMeals(){
        return bucket.allMeals();
    }
    @GetMapping("/isBucketEmpty")
    public boolean isBucketEmpty(){
      return bucket.isEmpty();
    }
}
