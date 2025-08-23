package com.salonms.salonmsBackend.repo;

import com.salonms.salonmsBackend.model.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepo extends MongoRepository<Staff, String> {
    Optional<Staff> findById(String id);

    boolean existsByStaffId(String staffId);

    Staff findByStaffId(String staffId);

    List<Staff> findByIsActive(boolean isActive);
}
