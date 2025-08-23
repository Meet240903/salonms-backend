package com.salonms.salonmsBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "appointments")
public class Appointments {
    @Id
    private String id;
    private String appointmentId;
    private String userId;
    private String staffId;
    private List<String> services;
    private String appointmentDate;
    private String appointmentTime;
    private String estimatedTimeToComplete;
    private String status;
    private boolean isActive = true;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
