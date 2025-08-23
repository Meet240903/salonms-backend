package com.salonms.salonmsBackend.repo;

import com.salonms.salonmsBackend.model.Appointments;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepo extends MongoRepository<Appointments, String> {
    Optional<Appointments> findById(String id);
    List<Appointments> findByIsActive(boolean isActive);
}
