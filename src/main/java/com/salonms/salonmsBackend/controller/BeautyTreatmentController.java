package com.salonms.salonmsBackend.controller;

import com.salonms.salonmsBackend.exception.BadRequestException;
import com.salonms.salonmsBackend.model.Service;
import com.salonms.salonmsBackend.service.BeautyTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@CrossOrigin(origins = "${allowed.domain}")
public class BeautyTreatmentController {

    private final BeautyTreatmentService beautyTreatmentService;

    @Autowired
    public BeautyTreatmentController(BeautyTreatmentService beautyTreatmentService) {
        this.beautyTreatmentService = beautyTreatmentService;
    }

    @PostMapping("/add-service")
    public ResponseEntity<Object> addNewService(@RequestBody Service service) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = beautyTreatmentService.addNewService(service);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/list-all-services")
    public ResponseEntity<Object> showAllServices() {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = beautyTreatmentService.showAllServices();
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/edit-service/{id}")
    public ResponseEntity<Object> editService(@PathVariable String id, @RequestBody Service service) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = beautyTreatmentService.editService(id, service);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/delete-service")
    public ResponseEntity<Object> deleteService(@RequestBody Service service) {
        ResponseEntity<Object> responseEntity;
        try {
            responseEntity = beautyTreatmentService.deleteService(service);
        } catch (BadRequestException badRequestException) {
            responseEntity = new ResponseEntity<>(badRequestException.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
