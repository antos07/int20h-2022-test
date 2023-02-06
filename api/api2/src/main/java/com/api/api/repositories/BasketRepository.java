package com.api.api.repositories;

import com.api.api.entities.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketRepository extends MongoRepository<Basket, Integer> {

}
