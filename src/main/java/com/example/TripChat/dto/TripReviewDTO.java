package com.example.TripChat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TripReviewDTO {
    private final String username;  // 작성자
    private final String title;  // 제목
    private final String content;  // 내용
    private final LocalDateTime createDate;  // 작성일
}
