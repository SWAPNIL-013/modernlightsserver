//package com.zosh.ai.services;
//
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//import java.util.Map;
//import java.util.List;
//
//@Service
//public class GeminiService {
//
//    private final WebClient webClient;
//    private final String apiKey = "AIzaSyBkcF0GoFZdxpA5CJkJuatEo8i0Qp4jn5s"; // Replace with your actual API key
//    private final String apiUrl = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash-001:generateContent";
//
//    public GeminiService(WebClient.Builder webClientBuilder) {
//        this.webClient = webClientBuilder.build();
//    }
//
//    public Mono<String> getHomeDecorSuggestions(String userQuery) {
//        return webClient.post()
//                .uri(apiUrl + "?key=" + apiKey)
//                .bodyValue(Map.of(
//                        "contents", List.of(Map.of(
//                                "parts", List.of(Map.of("text", userQuery))
//                        ))
//                ))
//                .retrieve()
//                .bodyToMono(Map.class)  // Convert JSON response to a Map
//                .map(response -> extractResponseText(response));
//    }
//
//    // Extracts response text safely
//    private String extractResponseText(Map<String, Object> response) {
//        try {
//            // Get "candidates" list
//            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
//            if (candidates == null || candidates.isEmpty()) {
//                return "No suggestions available.";
//            }
//
//            // Get first candidate's "content"
//            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
//            if (content == null) {
//                return "No content found.";
//            }
//
//            // Get "parts" list
//            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
//            if (parts == null || parts.isEmpty()) {
//                return "No response text found.";
//            }
//
//            // Extract and return text
//            return (String) parts.get(0).get("text");
//
//        } catch (Exception e) {
//            return "Error extracting response: " + e.getMessage();
//        }
//    }
//}
package com.modernlights.ai.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;
import java.util.List;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String apiKey = "AIzaSyBkcF0GoFZdxpA5CJkJuatEo8i0Qp4jn5s"; // Replace with your actual API key
    private final String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-pro-latest:generateContent";

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> getHomeDecorSuggestions(String userQuery) {
        return webClient.post()
                .uri(apiUrl + "?key=" + apiKey)
                .bodyValue(Map.of(
                        "contents", List.of(Map.of(
                                "parts", List.of(Map.of("text", userQuery))
                        ))
                ))
                .retrieve()
                .bodyToMono(Map.class)  // Convert JSON response to a Map
                .map(response -> extractResponseText(response));  // Extract and format the response text
    }

    // Extracts response text safely and formats it
    private String extractResponseText(Map<String, Object> response) {
        try {
            // Get "candidates" list
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
            if (candidates == null || candidates.isEmpty()) {
                return "No suggestions available.";
            }

            // Get first candidate's "content"
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            if (content == null) {
                return "No content found.";
            }

            // Get "parts" list
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            if (parts == null || parts.isEmpty()) {
                return "No response text found.";
            }

            // Extract text from response
            String responseText = (String) parts.get(0).get("text");

            // Format the response text
            return formatResponseText(responseText);  // Format response here

        } catch (Exception e) {
            return "Error extracting response: " + e.getMessage();
        }
    }

    // Method to format the response text
    private String formatResponseText(String responseText) {
        // Remove markdown stars (bold formatting)
        responseText = responseText.replaceAll("\\*\\*", "");

        // Remove bullet points (asterisks and any spaces after them)
        responseText = responseText.replaceAll("^\\*\\s*", "");

        // Optionally, remove or replace any remaining markdown characters
        responseText = responseText.replaceAll("[*]", "");

        responseText = responseText.replaceAll("^(#{1,2})\\s*", "");



        return responseText;
    }
}
