package com.salonms.salonmsBackend.controller;

import com.salonms.salonmsBackend.exception.BadRequestException;
import com.salonms.salonmsBackend.model.User;
import com.salonms.salonmsBackend.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/auth/registration")
@CrossOrigin(origins = "${allowed.domain}")
public class UserRegistrationController {
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("add-user")
    public ResponseEntity<Object> createNewUser(@RequestBody User user) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = userRegistrationService.createNewUser(user);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("get-customers")
    public ResponseEntity<Object> getAllCustomers() {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = userRegistrationService.getCustomers();
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("update-user")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
