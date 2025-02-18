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
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

        JwtAuthenticationFilter adminAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        adminAuthenticationFilter.setAuthenticationManager(authenticationManager);
        adminAuthenticationFilter.setFilterProcessesUrl("/api/adminLogin");

        JwtAuthenticationFilter userAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        userAuthenticationFilter.setAuthenticationManager(authenticationManager);
        userAuthenticationFilter.setFilterProcessesUrl("/api/userLogin");

        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/register").permitAll(); // Registro público
                    auth.requestMatchers("/api/adminLogin").permitAll(); // Permitir acceso al login de admin
                    auth.requestMatchers("/api/userLogin").permitAll();  // Permitir acceso al login de usuario
                    auth.requestMatchers("/api/admin/**").hasRole("ADMIN"); // Rutas protegidas para admin
                    auth.requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN");   // Rutas protegidas para usuarios
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(adminAuthenticationFilter) // Agregar filtro de admin
                .addFilter(userAuthenticationFilter)  // Agregar filtro de usuario
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

    @Configuration
    public class CorsConfig {

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
                            .allowedOrigins("http://localhost:4200", "http://localhost:5173", "http://localhost:8080")
                            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                            .allowedHeaders("*");
                }
            };
        }
    }
}