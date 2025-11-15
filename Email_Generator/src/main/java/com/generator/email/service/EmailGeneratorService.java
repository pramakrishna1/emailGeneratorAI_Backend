package com.generator.email.service;

import com.generator.email.model.EmailRequest;

public interface EmailGeneratorService {

    String generateEmail(EmailRequest emailRequest);
}
