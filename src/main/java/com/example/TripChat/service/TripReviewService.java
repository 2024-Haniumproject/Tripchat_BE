package com.example.TripChat.service;

import com.example.TripChat.dto.TripReviewDTO;
import com.example.TripChat.entity.TripReviewEntity;
import com.example.TripChat.repository.TripReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripReviewService {

    private final TripReviewRepository repository;

    public TripReviewEntity saveEntry(TripReviewDTO dto) {
        TripReviewEntity entity = new TripReviewEntity(dto.getUsername(), dto.getTitle(), dto.getContent(), dto.getCreateDate());
        return repository.save(entity);
    }
}