package com.salonms.salonmsBackend.service;

import com.salonms.salonmsBackend.model.Service;
import org.springframework.http.ResponseEntity;

public interface BeautyTreatmentService {
    ResponseEntity<Object> addNewService(Service service);
    ResponseEntity<Object> showAllServices();
}
