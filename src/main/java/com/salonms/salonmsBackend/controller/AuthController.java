package com.salonms.salonmsBackend.controller;

import com.salonms.salonmsBackend.model.User;
import com.salonms.salonmsBackend.model.dto.AuthResponse;
import com.salonms.salonmsBackend.repo.UserRepository;
import com.salonms.salonmsBackend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "${allowed.domain}")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User authRequest) {
        try {
            Optional<User> optionalUser = userRepository.findByUserName(authRequest.getUserName());

            if (optionalUser.isEmpty()) {
                return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
            }

            User user = optionalUser.get();
            if (!user.getRole().equalsIgnoreCase(authRequest.getRole())) {
                return new ResponseEntity<>("User does not exist with this role", HttpStatus.UNAUTHORIZED);
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
            String token = jwtUtil.generateToken(authRequest.getUserName(), authRequest.getRole());
            return new ResponseEntity<>(new AuthResponse(token, authRequest), HttpStatus.OK);
        } catch (AuthenticationException ex) {
            log.error("Authentication failed for user: {}. Reason: {}", authRequest.getUserName(), ex.getMessage());
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }
}