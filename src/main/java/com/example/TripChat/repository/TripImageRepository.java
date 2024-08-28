package com.example.TripChat.repository;

import com.example.TripChat.entity.TripImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripImageRepository extends JpaRepository<TripImageEntity, Long> {

}
