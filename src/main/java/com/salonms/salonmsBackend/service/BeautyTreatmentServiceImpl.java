package com.salonms.salonmsBackend.service;

import com.salonms.salonmsBackend.model.Service;
import com.salonms.salonmsBackend.repo.ServiceMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@org.springframework.stereotype.Service
public class BeautyTreatmentServiceImpl implements BeautyTreatmentService {

    private final ServiceMasterRepo serviceMasterRepo;

    @Autowired
    public BeautyTreatmentServiceImpl(ServiceMasterRepo serviceMasterRepo) {
        this.serviceMasterRepo = serviceMasterRepo;
    }

    @Override
    public ResponseEntity<Object> addNewService(Service service) {
        Optional<Service> optionalService = serviceMasterRepo.findByName(service.getName());
        if (optionalService.isEmpty()) {
            serviceMasterRepo.save(service);
            return new ResponseEntity<>(service, HttpStatus.OK);
        } else {
            Map<String, String> message = new HashMap<>();
            message.put("Message", "Username is already taken! Please try unique username.");
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> showAllServices() {
        List<Service> serviceList = serviceMasterRepo.findByIsActive(true);
        return new ResponseEntity<>(serviceList, HttpStatus.OK);
    }
}
