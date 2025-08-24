package com.salonms.salonmsBackend.controller;

import com.salonms.salonmsBackend.exception.BadRequestException;
import com.salonms.salonmsBackend.model.Appointments;
import com.salonms.salonmsBackend.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointment")
@CrossOrigin(origins = "${allowed.domain}")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("add-appointment")
    public ResponseEntity<Object> addNewAppointment(@RequestBody Appointments appointments) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = appointmentService.addNewAppointment(appointments);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("get-all-appointments")
    public ResponseEntity<Object> getAllAppointments() {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = appointmentService.getAllAppointments();
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/edit-appointment/{id}")
    public ResponseEntity<Object> editAppointment(@PathVariable String id, @RequestBody Appointments appointments) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = appointmentService.editAppointment(id, appointments);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/delete-appointment")
    public ResponseEntity<Object> deleteAppointment(@RequestBody Appointments appointments) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = appointmentService.deleteAppointment(appointments);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
