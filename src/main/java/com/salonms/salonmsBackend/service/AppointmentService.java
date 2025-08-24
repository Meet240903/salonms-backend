package com.salonms.salonmsBackend.service;

import com.salonms.salonmsBackend.model.Appointments;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {
    ResponseEntity<Object> addNewAppointment(Appointments appointments);
    ResponseEntity<Object> editAppointment(String id, Appointments appointments);
    ResponseEntity<Object> deleteAppointment(Appointments appointments);
    ResponseEntity<Object> getAllAppointments();
}
