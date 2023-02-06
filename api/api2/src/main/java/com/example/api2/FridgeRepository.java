package com.example.api2;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FridgeRepository extends MongoRepository<Fridge, Integer> {
}
