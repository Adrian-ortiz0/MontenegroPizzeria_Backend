package org.example.montenegropizzeria;

import org.example.montenegropizzeria.user.domain.ERole;
import org.example.montenegropizzeria.user.domain.RoleEntity;
import org.example.montenegropizzeria.user.domain.RoleEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MontenegroPizzeriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MontenegroPizzeriaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initRoles(RoleEntityRepository roleRepository) {
        return args -> {
            roleRepository.save(new RoleEntity(null, ERole.ADMIN));
            roleRepository.save(new RoleEntity(null, ERole.USER));
            System.out.println("Roles insertados: ADMIN, USER y GUEST.");
        };
    }

}
