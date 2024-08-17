package com.example.TripChat.service;

import com.example.TripChat.entity.UsersEntity;
import com.example.TripChat.repository.UserRepository;
import com.example.TripChat.dto.UsersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UsersDTO userDTO) {
        UsersEntity user = UsersEntity.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .nationality(userDTO.getNationality())
                .build();
        userRepository.save(user);
    }

    public UsersDTO findByUsername(String username) {
        UsersEntity user = userRepository.findByUsername(username);
        if (user != null) {
            return new UsersDTO(user.getUsername(), user.getPassword(), user.getEmail(), user.getNationality());
        }
        return null;
    }
}