package com.kuldeeplearning.hexagonal.user.adapters.outbound.persistence;

import com.kuldeeplearning.hexagonal.user.domain.User;
import com.kuldeeplearning.hexagonal.user.domain.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository repository;

    @Override
    public User save(User user) {

        UserEntity entity = new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );

        UserEntity saved = repository.save(entity);

        return map(saved);
    }

    @Override
    public Optional<User> findById(UUID id) {

        return repository.findById(id)
                .map(this::map);
    }

    private User map(UserEntity entity) {

        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail()
        );
    }
}
