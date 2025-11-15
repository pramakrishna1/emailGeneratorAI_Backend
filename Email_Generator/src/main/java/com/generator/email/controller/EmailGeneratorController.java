package com.generator.email.controller;

import com.generator.email.model.EmailRequest;
import com.generator.email.service.EmailGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class EmailGeneratorController {

    private EmailGeneratorService emailGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<String> generate(@RequestBody EmailRequest emailRequest){
        String response = emailGeneratorService.generateEmail(emailRequest);
        return ResponseEntity.ok(response);
    }

}
