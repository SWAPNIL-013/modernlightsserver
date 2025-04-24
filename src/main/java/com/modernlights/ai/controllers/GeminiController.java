//package com.zosh.ai.controllers;
//
//import com.zosh.ai.services.GeminiService;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//
//@RestController
//public class GeminiController {
//
//    private final GeminiService geminiService;
//
//    public GeminiController(GeminiService geminiService) {
//        this.geminiService = geminiService;
//    }
//
//    @GetMapping("/homedecor-suggestions")
//    public Mono<String> getDecorSuggestions(@RequestParam String userQuery) {
//        // If query is invalid, inform the user
//        if (!isValidQuery(userQuery)) {
//            return Mono.just("Sorry, I can only help with home decor, lighting, and interior design-related queries.");
//        }
//
//        // Prepend instruction to user query to guide Gemini for filtering responses
//        String prompt = """
//        💡 Welcome to Modern Lights Assistant — your guide to brightening up your home with stunning decor lighting ideas!
//
//        You are a smart assistant who ONLY answers questions related to home lighting and decor.
//        If a user asks something else, kindly reply with:
//        "Hey there!!!I'm here to help with lighting ideas! Please ask me something about home decor lighting."
//
//        User: %s
//        """.formatted(userQuery);
//
//        // Pass the modified prompt to GeminiService
//        return geminiService.getHomeDecorSuggestions(prompt);
//    }
//
//    private boolean isValidQuery(String userQuery) {
//        String lowerQuery = userQuery.toLowerCase();
//
//        // Relevant keywords for filtering home decor and lighting-related queries
//        String[] keywords = {
//                "hello","hi","hey","light", "lighting", "lamp", "bulb", "chandelier", "led", "string light", "tube light", "fancy light",
//                "garden light", "decor", "interior", "design", "room", "sofa", "furniture", "curtain", "wall decor",
//                "ceiling", "spotlight", "track light", "floor lamp", "table lamp", "pendant light", "ambient light",
//                "fairy lights", "solar lights", "bedroom", "living room", "hall", "bathroom", "mirror lights",
//                "pop design", "modular", "false ceiling", "fixture", "modern lights", "aesthetic", "home style",
//                "home accessories", "accent light", "home improvement", "garden", "outdoor light", "indoor light", "lighten"
//        };
//
//        for (String keyword : keywords) {
//            if (lowerQuery.contains(keyword)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
package com.modernlights.ai.controllers;

import com.modernlights.ai.services.GeminiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GeminiController {

    private final GeminiService geminiService;

    // Store conversation context
    private String conversationContext = "";

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping("/homedecor-suggestions")
    public Mono<String> getDecorSuggestions(@RequestParam String userQuery) {
        // Append the current user query to the conversation context
        conversationContext += "User: " + userQuery + "\n";

        // Prepend instruction to user query to guide Gemini for filtering responses
        String prompt = """
        💡 Welcome to Modern Lights Assistant — your guide to brightening up your home with stunning decor lighting ideas!

        You are a smart assistant who ONLY answers questions related to home lighting and decor.
        If a user asks something else, kindly reply with:
        "Hey there!!! I'm here to help with lighting ideas! Please ask me something about home decor lighting."

        Conversation so far:
        %s
        User: %s
        """.formatted(conversationContext, userQuery);

        // Pass the modified prompt to GeminiService
        return geminiService.getHomeDecorSuggestions(prompt)
                .doOnTerminate(() -> conversationContext = ""); // Clear context after each interaction
    }
}

