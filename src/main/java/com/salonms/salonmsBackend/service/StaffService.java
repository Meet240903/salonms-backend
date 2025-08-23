package com.salonms.salonmsBackend.service;

import com.salonms.salonmsBackend.model.Staff;
import org.springframework.http.ResponseEntity;

public interface StaffService {
    ResponseEntity<Object> addNewStaffMember(Staff staffRequest);
    ResponseEntity<Object> showStaffList();
    ResponseEntity<Object> editStaffDetails(String id, Staff staff);
    ResponseEntity<Object> deleteStaff(Staff staff);
}
