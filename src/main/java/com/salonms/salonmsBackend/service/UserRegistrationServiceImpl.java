package com.salonms.salonmsBackend.service;

import com.salonms.salonmsBackend.model.User;
import com.salonms.salonmsBackend.repo.UserRepository;
import com.salonms.salonmsBackend.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;
    private final UserUtils userUtils;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationServiceImpl(UserRepository userRepository, UserUtils userUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userUtils = userUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<Object> createNewUser(User user) {
        Optional<User> existedUser = userRepository.findByUserName(user.getUserName());
        Map<String, String> message = new HashMap<>();
        if (existedUser.isEmpty()) {
            user.setUserId(userUtils.generateUserId());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            message.put("Message", "User created successfully!");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            message.put("Message", "Username is already taken! Please try unique username.");
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getCustomers() {
        String role = "customer";
        List<User> userList = userRepository.findByRole(role);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
