package org.example.montenegropizzeria.user.infrastructure;

import org.example.montenegropizzeria.user.domain.RoleEntity;
import org.example.montenegropizzeria.user.domain.RoleEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleEntityRepository extends JpaRepository<RoleEntity, Long>, RoleEntityRepository {
}
