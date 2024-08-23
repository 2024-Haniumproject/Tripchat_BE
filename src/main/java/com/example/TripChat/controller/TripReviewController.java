package com.example.TripChat.controller;

import com.example.TripChat.dto.ResponseDTO;
import com.example.TripChat.dto.TripReviewDTO;
import com.example.TripChat.entity.TripReviewEntity;
import com.example.TripChat.service.TripReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tripreview")
@RequiredArgsConstructor
public class TripReviewController {

    private final TripReviewService service;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<TripReviewEntity>> createEntry(@RequestBody TripReviewDTO dto) {
        try {
            TripReviewEntity createdEntity = service.saveEntry(dto);
            ResponseDTO<TripReviewEntity> response = new ResponseDTO<>(
                    HttpStatus.CREATED.value(),
                    "성공했습니다",
                    createdEntity
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ResponseDTO<TripReviewEntity> response = new ResponseDTO<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "실패했습니다"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO<List<TripReviewEntity>>> getAllEntries() {
        try {
            List<TripReviewEntity> reviews = service.getAllEntries();
            ResponseDTO<List<TripReviewEntity>> response = new ResponseDTO<>(
                    HttpStatus.OK.value(),
                    "조회 성공했습니다",
                    reviews
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ResponseDTO<List<TripReviewEntity>> response = new ResponseDTO<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "조회 실패했습니다"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
