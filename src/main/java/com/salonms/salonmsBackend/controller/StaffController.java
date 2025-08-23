package com.salonms.salonmsBackend.controller;

import com.salonms.salonmsBackend.exception.BadRequestException;
import com.salonms.salonmsBackend.model.Staff;
import com.salonms.salonmsBackend.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("staff")
@CrossOrigin(origins = "${allowed.domain}")
public class StaffController {
    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("add-staff")
    public ResponseEntity<Object> addNewStaff(@RequestBody Staff staff) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = staffService.addNewStaffMember(staff);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("show-staff-list")
    public ResponseEntity<Object> showStaffList() {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = staffService.showStaffList();
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/edit-staff/{id}")
    public ResponseEntity<Object> editStaffDetails(@PathVariable String id, @RequestBody Staff staff) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = staffService.editStaffDetails(id, staff);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/delete-staff")
    public ResponseEntity<Object> deleteStaffDetails(@RequestBody Staff staff) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = staffService.deleteStaff(staff);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
