package com.salonms.salonmsBackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Errors {
    private String code;
    private String description;
    private String element;
    private Extention extention;
}
