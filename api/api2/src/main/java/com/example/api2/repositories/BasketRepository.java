package com.example.api2.repositories;

import com.example.api2.entities.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketRepository extends MongoRepository<Basket, Integer> {

}
