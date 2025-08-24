package com.salonms.salonmsBackend.service;

import com.salonms.salonmsBackend.model.Appointments;
import com.salonms.salonmsBackend.model.Staff;
import com.salonms.salonmsBackend.model.User;
import com.salonms.salonmsBackend.model.dto.AppointmentDetails;
import com.salonms.salonmsBackend.repo.AppointmentRepo;
import com.salonms.salonmsBackend.repo.StaffRepo;
import com.salonms.salonmsBackend.repo.UserRepository;
import com.salonms.salonmsBackend.util.DtoUtils;
import com.salonms.salonmsBackend.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final UserUtils userUtils;
    private final AppointmentRepo appointmentRepo;
    private final UserRepository userRepository;
    private final StaffRepo staffRepo;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AppointmentServiceImpl(UserUtils userUtils,
                                  AppointmentRepo appointmentRepo,
                                  MongoTemplate mongoTemplate,
                                  UserRepository userRepository,
                                  StaffRepo staffRepo) {
        this.userUtils = userUtils;
        this.appointmentRepo = appointmentRepo;
        this.mongoTemplate = mongoTemplate;
        this.userRepository = userRepository;
        this.staffRepo = staffRepo;
    }

    @Override
    public ResponseEntity<Object> addNewAppointment(Appointments appointments) {
        appointments.setAppointmentId(userUtils.generateAppointmentId());
        appointmentRepo.save(appointments);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllAppointments() {
        List<Appointments> appointmentsList = appointmentRepo.findByIsActive(true);
        List<AppointmentDetails> appointmentUpdatedList = !appointmentsList.isEmpty() ? appointmentsList.stream().map(appointments -> {
            User existedUser = userRepository.findByUserId(appointments.getUserId());
            String userName = DtoUtils.navigate(existedUser, User::getName, "");
            Staff existedStaff = staffRepo.findByStaffId(appointments.getStaffId());
            String staffName = DtoUtils.navigate(existedStaff, Staff::getName, "");

            return getAppointmentDetails(appointments, userName, staffName);
        }).toList() : List.of();
        return new ResponseEntity<>(appointmentUpdatedList, HttpStatus.OK);
    }

    private static AppointmentDetails getAppointmentDetails(Appointments appointments, String userName, String staffName) {
        AppointmentDetails response = new AppointmentDetails();
        response.setId(appointments.getId());
        response.setAppointmentId(appointments.getAppointmentId());
        response.setAppointmentDate(appointments.getAppointmentDate());
        response.setAppointmentTime(appointments.getAppointmentTime());
        response.setEstimatedTimeToComplete(appointments.getEstimatedTimeToComplete());
        response.setUserName(userName);
        response.setUserId(appointments.getUserId());
        response.setStaffName(staffName);
        response.setStaffId(appointments.getStaffId());
        response.setServices(appointments.getServices());
        response.setStatus(appointments.getStatus());
        return response;
    }

    @Override
    public ResponseEntity<Object> editAppointment(String id, Appointments appointments) {
        Optional<Appointments> optionalAppointments = appointmentRepo.findById(id);
        if (optionalAppointments.isPresent()) {
            Appointments existedAppointment = optionalAppointments.get();
            existedAppointment.setUserId(appointments.getUserId());
            existedAppointment.setStaffId(appointments.getStaffId());
            existedAppointment.setServices(appointments.getServices());
            existedAppointment.setAppointmentDate(appointments.getAppointmentTime());
            existedAppointment.setAppointmentTime(appointments.getAppointmentTime());
            existedAppointment.setEstimatedTimeToComplete(appointments.getEstimatedTimeToComplete());
            existedAppointment.setStatus(appointments.getStatus());

            appointmentRepo.save(existedAppointment);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Appointments details not found by given id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> deleteAppointment(Appointments appointments) {
        Optional<Appointments> optionalAppointments = appointmentRepo.findById(appointments.getId());
        if (optionalAppointments.isPresent()) {
            Appointments existedAppointment = optionalAppointments.get();
            existedAppointment.setActive(false);

            appointmentRepo.save(existedAppointment);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Appointment details not found by given id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
