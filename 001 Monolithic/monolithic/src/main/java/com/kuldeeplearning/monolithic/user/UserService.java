package com.kuldeeplearning.monolithic.user;

import com.kuldeeplearning.monolithic.report.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(
            String username,
            String email) {

        User user = User.builder()
                .id(UUID.randomUUID())
                .username(username)
                .email(email)
                .build();

        return userRepository.save(user);
    }
}