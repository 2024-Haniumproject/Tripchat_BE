package com.example.TripChat.controller;

import com.example.TripChat.dto.ResponseDTO;
import com.example.TripChat.dto.TripReviewDTO;
import com.example.TripChat.entity.TripImageEntity;
import com.example.TripChat.entity.TripReviewEntity;
import com.example.TripChat.repository.TripImageRepository;
import com.example.TripChat.repository.TripReviewRepository;
import com.example.TripChat.service.TripReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tripreview")
@RequiredArgsConstructor
public class TripReviewController {

    private final TripReviewService service;
    private final TripReviewRepository TripReviewRepository;
    private final TripImageRepository TripImageRepository;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<?>> createEntry(
            @RequestPart("review") TripReviewDTO dto,
            @RequestPart("images") List<MultipartFile> imageFiles) {
        try {
            TripReviewEntity reviewEntity = new TripReviewEntity(dto.getUsername(), dto.getTitle(), dto.getContent(), dto.getCreateDate());
            TripReviewRepository.save(reviewEntity);

            for (MultipartFile file : imageFiles) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                // 절대 경로 지정
                String filePath = "C:/uploads/" + fileName;  // 또ㅗ는 다른 절대 경로를 사용
                File dest = new File(filePath);

                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs(); // 경로가 존재 X -> 디렉토리 생성
                }

                try {
                    System.out.println("Uploading file: " + file.getOriginalFilename() + " (" + file.getSize() + " bytes)");
                    file.transferTo(dest);
                    System.out.println("File saved successfully at: " + dest.getAbsolutePath());

                    TripImageEntity imageEntity = new TripImageEntity(filePath);
                    imageEntity.setReview(reviewEntity);
                    TripImageRepository.save(imageEntity);
                } catch (IOException e) {
                    System.err.println("Failed to save file at: " + dest.getAbsolutePath());
                    e.printStackTrace();
                    throw new RuntimeException("File upload failed", e);
                }
            }

            ResponseDTO<?> response = new ResponseDTO<>(HttpStatus.CREATED.value(), "성공했습니다", null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            ResponseDTO<?> response = new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "실패했습니다");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDTO<List<TripReviewDTO>>> getAllEntries() {
        try {
            List<TripReviewDTO> reviews = service.getAllEntries();
            ResponseDTO<List<TripReviewDTO>> response = new ResponseDTO<>(HttpStatus.OK.value(), "조회 성공했습니다", reviews);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ResponseDTO<List<TripReviewDTO>> response = new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "조회 실패했습니다");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
