package com.api.api.repositories;

import com.api.api.entities.Fridge;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FridgeRepository extends MongoRepository<Fridge, Integer> {
}
