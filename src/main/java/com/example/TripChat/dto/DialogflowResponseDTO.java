package com.example.TripChat.dto;

import com.example.TripChat.entity.DialogflowEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DialogflowResponseDTO {
    private String queryText;
    private String intentName;
    private String fulfillmentText;
    private String nation;

    public static DialogflowResponseDTO fromEntity(DialogflowEntity entity) {
        return new DialogflowResponseDTO(
                entity.getQueryText(),
                entity.getIntentName(),
                entity.getFulfillmentText(),
                entity.getNation()
        );
    }
}
