//package com.zosh.ai.controllers;
//
//import com.zosh.ai.services.AiProductService;
//import com.zosh.response.ApiResponse;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/ai")
//public class AiProductController {
//
//    private final AiProductService productService;
//
//    public AiProductController(AiProductService productService) {
//        this.productService = productService;
//    }
//
//    @PostMapping("/chat/demo")
//    public ApiResponse generate(@RequestParam(
//            value = "message",
//            defaultValue = "Tell me a joke") String message) throws Exception {
//
//        // Get response from AI service
//        String ans = productService.simpleChat(message);
//
//        // Debugging: Print the response to check format
//        System.out.println("Response from AI Service: " + ans);
//
//        try {
//            // Ensure the response is a valid JSON before parsing
//            JSONObject messageJson = new JSONObject(ans);
//
//            JSONArray candidates = messageJson.getJSONArray("candidates");
//            JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
//            JSONArray parts = content.getJSONArray("parts");
//            String text = parts.getJSONObject(0).getString("text");
//
//            // Build API response
//            ApiResponse apiResponse = new ApiResponse();
//            apiResponse.setMessage(text);
//
//            return apiResponse;
//        } catch (Exception e) {
//            // Return an error response if JSON parsing fails
//            return new ApiResponse("Error processing AI response: " + e.getMessage());
//        }
//    }
//}
