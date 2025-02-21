package com.ragheb.chat;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QnAService {

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    // Access to APIKey and URL [GEMINI]
    @Value("${gemini.api.url}")
    private String geminiApiUrl;
    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder.build();
    }

    @PostConstruct
    public void printEnvVars() {
        System.out.println("GEMINI_API_URL = " + geminiApiUrl);
        System.out.println("GEMINI_API_KEY = " + geminiApiKey);
    }

    public String getAnswer(String question) {
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                        Map.of("text", question)
                                )
                        )
                )
        );

        try {
            return webClient.post()
                    .uri(geminiApiUrl + "?key=" + geminiApiKey)  // Corrected API Key usage
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> {
                        System.out.println("Client Error: " + response.statusCode());
                        return Mono.error(new RuntimeException("API Client Error: " + response.statusCode()));
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, response -> {
                        System.out.println("Server Error: " + response.statusCode());
                        return Mono.error(new RuntimeException("API Server Error: " + response.statusCode()));
                    })
                    .bodyToMono(String.class)
                    .block(); // Blocking call for simplicity, use reactive approach if needed
        } catch (Exception e) {
            return "Error calling Gemini API: " + e.getMessage();
        }
    }
}

