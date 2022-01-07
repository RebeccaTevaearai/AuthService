package com.example.consumingrest.data;

import com.example.consumingrest.data.Account;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {
    private String token;
    private Account account;

    public LoginResponse(String token, Account account) {
        this.account = account;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "{\n" +
                " \"token\": \"" + token + "\",\n" +
                " \"account\": " + account.toString() + "\n" +
                "}";
    }
}
