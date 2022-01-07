package com.example.consumingrest.data;

public class RegisterResponse {
    private Long id;
    private String username;
    private String role;

    public RegisterResponse(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{\n" +
                " \"id\": \"" + id + "\",\n" +
                " \"username\": \"" + username + "\",\n" +
                " \"role\": \"" + role + "\"\n" +
                "}";
    }
}
