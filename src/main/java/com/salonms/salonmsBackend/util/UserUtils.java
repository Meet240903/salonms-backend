package com.salonms.salonmsBackend.util;

import java.util.Random;
import java.util.UUID;

import com.salonms.salonmsBackend.repo.StaffRepo;
import com.salonms.salonmsBackend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffRepo staffRepo;

    private final Random random = new Random();

    public String generateUserId() {
        String userId;
        do {
            int number = 100000 + random.nextInt(900000); // 100000–999999
            userId = String.valueOf(number);
        } while (userRepository.existsByUserId(userId)); // Check uniqueness in DB

        return userId;
    }

    public String generateStaffId() {
        String staffId;
        do {
            int number = 100000 + random.nextInt(900000); // 100000–999999
            staffId = "E" + number;
        } while (staffRepo.existsByStaffId(staffId)); // Check uniqueness in DB

        return staffId;
    }

    public String generateAppointmentId() {
        String randomPart = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6).toUpperCase();
        return "APT-" + randomPart;
    }
}