package com.example.TripChat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class TripReviewDTO {
    private final String username;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;

    private final List<String> images; // 이미지 경로만 저장
}

