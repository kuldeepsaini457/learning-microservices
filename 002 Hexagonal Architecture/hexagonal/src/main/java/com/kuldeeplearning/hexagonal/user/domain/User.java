package com.kuldeeplearning.hexagonal.user.domain;

import java.util.UUID;

public class User {

    private final UUID id;
    private String username;
    private String email;

    public User(
            UUID id,
            String username,
            String email) {

        this.id = id;
        this.username = username;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void changeEmail(String email) {

        if (email == null || email.isBlank()) {

            throw new IllegalArgumentException(
                    "Email cannot be blank"
            );
        }

        this.email = email;
    }

    public void changeUsername(String username) {

        if (username == null || username.isBlank()) {

            throw new IllegalArgumentException(
                    "Username cannot be blank"
            );
        }

        this.username = username;
    }
}