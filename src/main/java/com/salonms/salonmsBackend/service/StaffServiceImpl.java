package com.salonms.salonmsBackend.service;

import com.salonms.salonmsBackend.model.Staff;
import com.salonms.salonmsBackend.repo.StaffRepo;
import com.salonms.salonmsBackend.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepo staffRepo;
    private final UserUtils userUtils;

    @Autowired
    public StaffServiceImpl(StaffRepo staffRepo, UserUtils userUtils) {
        this.staffRepo = staffRepo;
        this.userUtils = userUtils;
    }

    @Override
    public ResponseEntity<Object> addNewStaffMember(Staff staffRequest) {
        staffRequest.setStaffId(userUtils.generateStaffId());
        staffRepo.save(staffRequest);
        return new ResponseEntity<>(staffRequest, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> showStaffList() {
        List<Staff> staffList = staffRepo.findByIsActive(true);
        return new ResponseEntity<>(staffList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> editStaffDetails(String id, Staff staff) {
        Optional<Staff> optionalStaff = staffRepo.findById(id);
        if (optionalStaff.isPresent()) {
            Staff existedStaff = optionalStaff.get();
            existedStaff.setName(staff.getName());
            existedStaff.setEmail(staff.getEmail());
            existedStaff.setPhone(staff.getPhone());
            existedStaff.setRoles(staff.getRoles());

            staffRepo.save(existedStaff);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Staff details not found by given id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> deleteStaff(Staff staff) {
        Optional<Staff> optionalStaff = staffRepo.findById(staff.getId());
        if (optionalStaff.isPresent()) {
            Staff existedStaff = optionalStaff.get();
            existedStaff.setActive(false);

            staffRepo.save(existedStaff);
            return new ResponseEntity<>(staff, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Staff details not found by given id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
