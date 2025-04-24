package com.modernlights.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

	private String message;
	private boolean status;


    public ApiResponse(String s) {
    }

}
