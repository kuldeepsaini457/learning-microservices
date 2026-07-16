package com.kuldeeplearning.hexagonal.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(
            @RequestParam String username,
            @RequestParam String email) {

        return userService.createUser(
                username,
                email
        );
    }
}