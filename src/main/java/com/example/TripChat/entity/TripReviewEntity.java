package com.example.TripChat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class TripReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;  // 작성자
    private String title;  // 제목
    private String content;  // 내용
    private LocalDateTime createDate;  // 작성일

    public TripReviewEntity(String username, String title, String content, LocalDateTime createDate) {
        this.username=username;
        this.title=title;
        this.content=content;
        this.createDate=createDate;
    }
}
