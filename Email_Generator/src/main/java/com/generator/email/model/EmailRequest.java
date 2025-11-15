package com.generator.email.model;

import lombok.Data;

@Data
public class EmailRequest {
    private String emailContent;
    private String tone;
}
