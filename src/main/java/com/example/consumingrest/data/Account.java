package com.example.consumingrest.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    private Long id;
    private String username;
    private String role;

    public Account(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{\n" +
                " \"id\": " + id + ",\n" +
                " \"username\": \"" + username + "\",\n" +
                " \"role\": \"" + role + "\"\n" +
                "}";
    }

}
