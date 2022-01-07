package com.example.consumingrest.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    private String error;
    
    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "{\n" +
                " \"error\": \"" + error + "\"\n" +
                "}";
    }
}
