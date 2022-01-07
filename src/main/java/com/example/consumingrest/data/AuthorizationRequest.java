package com.example.consumingrest.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizationRequest {
    private String ressource;
    private String token;
    
    public AuthorizationRequest() {}

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "{\n" +
                " \"ressource\": \"" + ressource + "\",\n" +
                " \"token\": \"" + token + "\"\n" +
                "}";
    }
}
