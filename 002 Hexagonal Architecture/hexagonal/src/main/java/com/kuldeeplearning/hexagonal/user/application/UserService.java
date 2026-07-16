package com.kuldeeplearning.hexagonal.user.application;

import com.kuldeeplearning.hexagonal.user.domain.User;
import com.kuldeeplearning.hexagonal.user.domain.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final UserRepositoryPort repository;

    @Override
    public User createUser(String username, String email) {

        User user = new User(
                UUID.randomUUID(),
                username,
                email
        );

        return repository.save(user);
    }
}
