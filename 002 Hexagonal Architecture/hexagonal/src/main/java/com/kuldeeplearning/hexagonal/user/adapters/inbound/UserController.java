package com.kuldeeplearning.hexagonal.user.adapters.inbound;

import com.kuldeeplearning.hexagonal.user.application.CreateUserUseCase;
import com.kuldeeplearning.hexagonal.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public User createUser(
            @RequestParam String username,
            @RequestParam String email) {

        return createUserUseCase.createUser(username, email);
    }
}