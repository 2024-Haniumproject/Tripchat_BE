package com.example.TripChat.controller;

import com.example.TripChat.dto.DialogflowResponseDTO;
import com.example.TripChat.dto.ResponseDTO;
import com.example.TripChat.service.DialogflowService;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dialogflow")
@RequiredArgsConstructor
public class DialogflowController {

    private final DialogflowService dialogflowService;

    @PostMapping("/intent")
    public ResponseEntity<ResponseDTO<DialogflowResponseDTO>> detectIntent(@RequestBody Map<String, String> requestPayload) {
        String text = requestPayload.get("text");
        String sessionId = requestPayload.get("sessionId");

        try {
            DialogflowResponseDTO responseDto = dialogflowService.detectIntentTexts(text, sessionId);
            ResponseDTO<DialogflowResponseDTO> response = new ResponseDTO<>(
                    HttpStatus.OK.value(),
                    "Dialogflow 데이터를 성공적으로 불러왔습니다.",
                    responseDto
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseDTO<DialogflowResponseDTO> errorResponse = new ResponseDTO<>(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error: " + e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

