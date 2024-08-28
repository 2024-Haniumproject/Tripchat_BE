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
        private String name;
        private String hours;
        private double rating;

        @Field("average_waiting_time")
        private String averageWaitingTime;

        @Field("image_url")
        private String imageUrl;


        @Field("closed_on")
        private String closedOn;

        @Field("google_maps_link")
        private String googleMapsLink;

        public Response(String name, String averageWaitingTime, double rating, String imageUrl, String hours, String closedOn, String googleMapsLink) {
            this.name = name;
            this.averageWaitingTime = averageWaitingTime;
            this.rating = rating;
            this.imageUrl = imageUrl;
            this.hours = hours;
            this.closedOn = closedOn;
            this.googleMapsLink = googleMapsLink;
        }
    }

    public MongoEntity(String intentName, String nation, String who, Response response) {
        this.intentName = intentName;
        this.nation = nation;
        this.who = who;
        this.response = response;
    }
}

