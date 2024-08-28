package com.example.TripChat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class TripReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String title;
    private String content;
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TripImageEntity> images = new ArrayList<>();

    public TripReviewEntity(String username, String title, String content, LocalDateTime createDate) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }

    public void addImage(TripImageEntity image) {
        images.add(image);
        image.setReview(this);
    }
}
