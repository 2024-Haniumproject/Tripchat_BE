package com.example.TripChat.repository;

import com.example.TripChat.entity.MongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DialogflowResponseRepository extends MongoRepository<MongoEntity, String> {
    // 특정 조건에 맞는 첫 번째 응답을 가져오는 메소드
    Optional<MongoEntity> findFirstByIntentNameAndNationAndWho(String intentName, String nation, String who);
}