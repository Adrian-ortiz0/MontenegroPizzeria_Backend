package org.example.montenegropizzeria.user.infrastructure;

import org.example.montenegropizzeria.user.domain.UserEntity;
import org.example.montenegropizzeria.user.domain.UserEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserEntityRepository extends JpaRepository<UserEntity, Long>, UserEntityRepository {
}
