package com.example.TripChat.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor
@Document(collection = "dialogflow_responses")
public class MongoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String intentName; //질문 유형
    private String nation; //나라
    private String who;

    private Response response;

    @Getter
    @NoArgsConstructor
    public static class Response {
        private String name; //이름
        private String hours; // 음식점 운영 시간
        private double rating; //별점
        private String location; //위치
        private String Booking; //예약 사이트
        private String stars; //몇성급 호텔

        @Field("average_waiting_time")
        private String averageWaitingTime; //음식점 평균 웨이팅 시간

        @Field("image_url")
        private String imageUrl; //이미지

        @Field("closed_on")
        private String closedOn; //음식점 닫는 시간

        @Field("google_maps_link")
        private String googleMapsLink; //구글맵

        @Field("average_price")
        private String averagePrice; //호텔 가격

        public Response(String name, String averageWaitingTime, double rating, String imageUrl, String hours, String closedOn, String googleMapsLink, String location, String Booking, String stars, String averagePrice) {
            this.name = name;
            this.averageWaitingTime = averageWaitingTime;
            this.rating = rating;
            this.imageUrl = imageUrl;
            this.hours = hours;
            this.closedOn = closedOn;
            this.googleMapsLink = googleMapsLink;

            this.location = location;
            this.Booking = Booking;
            this.stars = stars;
            this.averagePrice = averagePrice;
        }
    }

    public MongoEntity(String intentName, String nation, String who, Response response) {
        this.intentName = intentName;
        this.nation = nation;
        this.who = who;
        this.response = response;
    }
}

