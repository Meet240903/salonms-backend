package com.salonms.salonmsBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    @Id
    private String id;
    private String name;
    private String description;
    private String duration;
    private boolean isActive = true;
}
