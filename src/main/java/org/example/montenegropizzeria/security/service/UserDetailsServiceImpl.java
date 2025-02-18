package org.example.montenegropizzeria.security.service;

import org.example.montenegropizzeria.user.domain.UserEntity;
import org.example.montenegropizzeria.user.domain.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Cambiado para buscar por email en vez de username
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe"));

        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(roleEntity -> new SimpleGrantedAuthority("ROLE_".concat(roleEntity.getName().name())))
                .collect(Collectors.toSet());

        return new User(userEntity.getEmail(), userEntity.getPassword(),
                true, true, true, true, authorities);
    }
}
