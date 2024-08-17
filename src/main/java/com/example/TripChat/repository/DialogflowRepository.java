package com.example.TripChat.repository;

import com.example.TripChat.entity.DialogflowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DialogflowRepository extends JpaRepository<DialogflowEntity, Long> {
}
