package com.kuldeeplearning.hexagonal.user.domain;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    User save(User user);

    Optional<User> findById(UUID id);
}
