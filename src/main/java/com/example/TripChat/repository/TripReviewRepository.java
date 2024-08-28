package com.example.TripChat.repository;


import com.example.TripChat.entity.TripReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripReviewRepository extends JpaRepository<TripReviewEntity, Long> {
}
