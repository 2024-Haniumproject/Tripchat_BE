package com.example.TripChat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Getter
@NoArgsConstructor
public class DialogflowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queryText; //사용자가 한 질문
    private String intentName; //질문 유형
    private String fulfillmentText; //답변
    private String nation; //나라
    private String who;

    public DialogflowEntity(String queryText, String intentName, String fulfillmentText, String nation, String who) {
        this.queryText = queryText;
        this.intentName = intentName;
        this.fulfillmentText = fulfillmentText;
        this.nation = nation;
        this.who = who;
    }
}