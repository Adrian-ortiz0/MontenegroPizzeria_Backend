package org.example.montenegropizzeria.user.domain;


import java.util.Optional;

public interface RoleEntityRepository {
    Optional<RoleEntity> findByName(ERole role);
    RoleEntity save(RoleEntity role);
}
