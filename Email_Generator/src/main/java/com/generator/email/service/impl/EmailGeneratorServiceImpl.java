package com.generator.email.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.email.model.EmailRequest;
import com.generator.email.service.EmailGeneratorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorServiceImpl implements EmailGeneratorService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiUrl;
    @Value("${gemini.api.key}")
    private String geminiKey;

    public EmailGeneratorServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }


    @Override
    public String generateEmail(EmailRequest emailRequest) {
        //Generating the Prompt
        String prompt = generatePrompt(emailRequest);
        //Crafting a request
        Map<String, Object> requestBody = Map.of(
             "contents", new Object[]{
                     Map.of("parts", new Object[]{
                             Map.of("text", prompt)
                     })
                }
        );
        //Do request and get a response from gemini api
        String response = webClient.post()
                .uri(geminiUrl + geminiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        //Extract and Return Response
        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
       try{
           ObjectMapper mapper = new ObjectMapper();
           JsonNode rootNode = mapper.readTree(response);
           return rootNode.path("candidates")
                   .get(0)
                   .path("content")
                   .path("parts")
                   .get(0)
                   .path("text")
                   .asText();
       }catch(Exception e){
           return "Error processing" + e.getMessage();
       }
    }

    private String generatePrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following content. Please don't generate a subject line ");
        String tone = emailRequest.getTone();
        if(tone != null && !tone.isBlank()){
            prompt.append("Use a ").append(tone).append(" tone.");
        }
        prompt.append("\nOriginal email: \n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
