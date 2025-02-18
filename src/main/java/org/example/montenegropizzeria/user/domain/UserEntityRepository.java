package org.example.montenegropizzeria.user.domain;

import java.util.Optional;

public interface UserEntityRepository {
    Optional<UserEntity> findByEmail(String email);
    UserEntity save(UserEntity user);
}
