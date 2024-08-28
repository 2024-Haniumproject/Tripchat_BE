package com.example.TripChat.service;

import com.example.TripChat.dto.TripReviewDTO;
import com.example.TripChat.entity.TripImageEntity;
import com.example.TripChat.entity.TripReviewEntity;
import com.example.TripChat.repository.TripImageRepository;
import com.example.TripChat.repository.TripReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripReviewService {

    private final TripReviewRepository reviewRepository;
    private final TripImageRepository imageRepository;

    public TripReviewDTO saveEntry(TripReviewDTO dto, List<MultipartFile> imageFiles) throws IOException {
        TripReviewEntity reviewEntity = new TripReviewEntity(dto.getUsername(), dto.getTitle(), dto.getContent(), dto.getCreateDate());

        // 리뷰 엔티티 먼저 저장
        reviewRepository.save(reviewEntity);

        // 이미지 저장
        for (MultipartFile file : imageFiles) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath = "images/" + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);

            TripImageEntity imageEntity = new TripImageEntity(filePath);
            reviewEntity.addImage(imageEntity);
            imageRepository.save(imageEntity);
        }

        return new TripReviewDTO(
                reviewEntity.getUsername(),
                reviewEntity.getTitle(),
                reviewEntity.getContent(),
                reviewEntity.getCreateDate(),
                reviewEntity.getImages().stream()
                        .map(TripImageEntity::getFilePath)
                        .collect(Collectors.toList())
        );
    }

    public List<TripReviewDTO> getAllEntries() {
        return reviewRepository.findAll().stream()
                .map(review -> new TripReviewDTO(
                        review.getUsername(),
                        review.getTitle(),
                        review.getContent(),
                        review.getCreateDate(),
                        review.getImages().stream()
                                .map(TripImageEntity::getFilePath)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}

