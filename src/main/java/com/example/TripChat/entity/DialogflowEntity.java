package com.example.TripChat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class DialogflowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queryText;
    private String intentName;
    private String fulfillmentText;
    private String nation;

    public DialogflowEntity(String queryText, String intentName, String fulfillmentText, String nation) {
        this.queryText = queryText;
        this.intentName = intentName;
        this.fulfillmentText = fulfillmentText;
        this.nation = nation;
    }
}