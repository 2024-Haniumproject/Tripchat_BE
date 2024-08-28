package com.example.TripChat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private int status;
    private String message;
    private T data;

    public ResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }
}
