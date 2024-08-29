package com.example.TripChat.dto;

import com.example.TripChat.entity.DialogflowEntity;
import com.example.TripChat.entity.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DialogflowResponseDTO {
    private String queryText; //사용자가 한 질문
    private String intentName; //질문 유형
    private String fulfillmentText; //답변
    private String nation; //나라
    private String who; //누구랑

    private String name;
    private String averageWaitingTime;
    private double rating;
    private String imageUrl;
    private String hours;
    private String closedOn;
    private String googleMapsLink;

    private String location; //위치
    private String Booking; //예약 사이트
    private String stars; //몇성급 호텔

    private String averagePrice; //호텔 가격

    public static DialogflowResponseDTO fromEntity(DialogflowEntity entity) {
        return new DialogflowResponseDTO(
                entity.getQueryText(),
                entity.getIntentName(),
                entity.getFulfillmentText(),
                entity.getNation(),
                entity.getWho(),
                null,
                null,
                0.0,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public static DialogflowResponseDTO fromEntityWithMongoData(DialogflowEntity entity, MongoEntity mongoResponse) {
        return new DialogflowResponseDTO(
                entity.getQueryText(),
                entity.getIntentName(),
                entity.getFulfillmentText(),
                entity.getNation(),
                entity.getWho(),
                mongoResponse.getResponse().getName(),
                mongoResponse.getResponse().getAverageWaitingTime(),
                mongoResponse.getResponse().getRating(),
                mongoResponse.getResponse().getImageUrl(),
                mongoResponse.getResponse().getHours(),
                mongoResponse.getResponse().getClosedOn(),
                mongoResponse.getResponse().getGoogleMapsLink(),

                mongoResponse.getResponse().getLocation(),
                mongoResponse.getResponse().getBooking(),
                mongoResponse.getResponse().getStars(),
                mongoResponse.getResponse().getAveragePrice()
        );
    }
}
