package org.example.montenegropizzeria.security.filters;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.montenegropizzeria.security.jwt.JwtUtils;
import org.example.montenegropizzeria.user.domain.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UserEntity userEntity = null;
        String email = "";
        String password = "";
        try{
            userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            email = userEntity.getEmail();
            password = userEntity.getPassword();

        } catch (DatabindException | StreamReadException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        if(request.getServletPath().equals("/api/adminLogin")) {
            boolean isAdmin = user.getAuthorities().stream()
                    .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

            if(!isAdmin) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.getWriter().write("{\"error\": \"Solo administradores pueden acceder\"}");
                response.setContentType("application/json");
                return;
            }
        }
        String token = jwtUtils.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message", "Authentication successful");
        httpResponse.put("Email", user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
