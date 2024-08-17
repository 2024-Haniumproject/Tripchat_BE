package com.example.TripChat.service;

import com.example.TripChat.dto.DialogflowResponseDTO;
import com.example.TripChat.entity.DialogflowEntity;
import com.example.TripChat.repository.DialogflowRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DialogflowService {

    private final DialogflowRepository dialogflowResponseRepository;

    private static final String PROJECT_ID = "tripchat-wpwp";

    public DialogflowResponseDTO detectIntentTexts(String text, String sessionId) throws IOException {
        DetectIntentResponse response = getDialogflowResponse(text, sessionId);
        Map<String, String> parsedResponse = parseResponse(response);

        DialogflowEntity entity = new DialogflowEntity(
                parsedResponse.get("Our text"),
                parsedResponse.get("Dialogflow's intent"),
                parsedResponse.get("Dialogflow's response"),
                parsedResponse.get("Dialogflow's nation")
        );

        dialogflowResponseRepository.save(entity);

        return DialogflowResponseDTO.fromEntity(entity);
    }

    private DetectIntentResponse getDialogflowResponse(String text, String sessionId) throws IOException {
        SessionsClient sessionsClient = getSessionsClient();
        SessionName session = SessionName.of(PROJECT_ID, sessionId);

        TextInput.Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode("ko");
        QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

        DetectIntentRequest detectIntentRequest = DetectIntentRequest.newBuilder()
                .setSession(session.toString())
                .setQueryInput(queryInput)
                .build();

        return sessionsClient.detectIntent(detectIntentRequest);
    }

    private SessionsClient getSessionsClient() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/private_key.json"));
        SessionsSettings sessionsSettings = SessionsSettings.newBuilder().setCredentialsProvider(() -> credentials).build();
        return SessionsClient.create(sessionsSettings);
    }

    private Map<String, String> parseResponse(DetectIntentResponse response) {
        QueryResult queryResult = response.getQueryResult();
        String intentName = queryResult.getIntent().getDisplayName();
        String fulfillmentText = queryResult.getFulfillmentText();
        Struct parameters = queryResult.getParameters();
        Map<String, Value> fieldsMap = parameters.getFieldsMap();

        String nation = fieldsMap.getOrDefault("nation", Value.newBuilder().setStringValue("Unknown").build()).getStringValue();

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("Our text", queryResult.getQueryText());
        responseMap.put("Dialogflow's response", fulfillmentText);
        responseMap.put("Dialogflow's intent", intentName);
        responseMap.put("Dialogflow's nation", nation);

        return responseMap;
    }
}
