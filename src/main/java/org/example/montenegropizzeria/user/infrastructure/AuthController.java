package org.example.montenegropizzeria.user.infrastructure;

import org.example.montenegropizzeria.user.domain.*;
import org.example.montenegropizzeria.user.domain.DTO.CreateUserDTO;
import org.example.montenegropizzeria.user.domain.DTO.LoginAdminDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final RoleEntityRepository roleRepository;
    private final UserEntityRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(RoleEntityRepository roleRepository, UserEntityRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateUserDTO createUserDTO) {

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(roleName -> roleRepository.findByName(ERole.valueOf(roleName))
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        UserEntity userEntity = new UserEntity();
        userEntity.setName(createUserDTO.getName());
        userEntity.setPhone(createUserDTO.getPhone());
        userEntity.setEmail(createUserDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        userEntity.setAddress(createUserDTO.getAddress());
        userEntity.setRoles(roles);

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }
}
