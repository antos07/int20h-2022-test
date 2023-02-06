package com.example.api2.repositories;

import com.example.api2.entities.Fridge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FridgeRepository extends MongoRepository<Fridge, Integer> {
}
