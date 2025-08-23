package com.salonms.salonmsBackend.repo;

import com.salonms.salonmsBackend.model.Service;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceMasterRepo extends MongoRepository<Service, String> {
    Optional<Service> findByName(String name);

    List<Service> findByIsActive(boolean isActive);
}
