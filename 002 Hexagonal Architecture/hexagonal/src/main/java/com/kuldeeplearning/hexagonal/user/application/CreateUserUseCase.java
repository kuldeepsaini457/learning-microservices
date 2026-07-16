package com.kuldeeplearning.hexagonal.user.application;

import com.kuldeeplearning.hexagonal.user.domain.User;

public interface CreateUserUseCase {

    User createUser(String username, String email);
}
