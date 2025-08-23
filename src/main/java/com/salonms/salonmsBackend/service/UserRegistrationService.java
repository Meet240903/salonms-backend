package com.salonms.salonmsBackend.service;

import com.salonms.salonmsBackend.model.User;
import org.springframework.http.ResponseEntity;

public interface UserRegistrationService {
    ResponseEntity<Object> createNewUser(User user);
    ResponseEntity<Object> getCustomers();
}
