package com.salonms.salonmsBackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetails {
    private String id;
    private String appointmentId;
    private String userName;
    private String userId;
    private String staffName;
    private String staffId;
    private List<String> services;
    private String appointmentDate;
    private String appointmentTime;
    private String estimatedTimeToComplete;
    private String status;
}
