package com.karen.repository;

import com.karen.model.mongo.BoardConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardConfigRepository extends MongoRepository<BoardConfig, String> {
}
