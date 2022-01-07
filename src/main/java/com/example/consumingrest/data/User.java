package com.example.consumingrest.data;

public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final String role;
    
    public User(long id, String username, String password, String role) {
        this.id = id;
        this. username = username;
        this. password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "{\n" +
                " \"id\": \"" + id + "\",\n" +
                " \"username\": \"" + username + "\",\n" +
                " \"password\": \"" + password + "\",\n" +
                " \"role\": \"" + role + "\"\n" +
                "}";
    }
}
