package com.example.TripChat.dto;

import com.example.TripChat.entity.NationalityEnum;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    private String username;
    private String password;
    private String email;
    private NationalityEnum nationality;
}
