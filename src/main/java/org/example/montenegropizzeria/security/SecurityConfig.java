package org.example.montenegropizzeria.security;

import org.example.montenegropizzeria.security.filters.JwtAuthenticationFilter;
import org.example.montenegropizzeria.security.filters.JwtAuthorizationFilter;
import org.example.montenegropizzeria.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final JwtUtils jwtUtils;

    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, JwtUtils jwtUtils, JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/api/register").permitAll();
                    auth.requestMatchers("/api/adminLogin").permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session ->{
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}