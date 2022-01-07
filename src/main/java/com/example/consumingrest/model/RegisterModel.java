package com.example.consumingrest.model;

import com.example.consumingrest.DatabaseConnection;
import com.example.consumingrest.data.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RegisterModel {

    static public Boolean isUsernameFree(String username) throws Exception {

        try {
            ResultSet resultSet = DatabaseConnection.doQuery(
                    "SELECT * FROM `USER` WHERE `username` = ?",
                    new ArrayList<String>() {{add(username);}}
            );

            // check if exist
            if (resultSet.next()) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    static public Boolean isPasswordValid(String password) {
        if (password.length() < 7 || password.length() > 128) {
            return false;
        }

        return true;
    }

    static public void createAccount(String username, String password, String role) throws Exception {
        try {

            String sql = "INSERT INTO `USER` (username, password, role) values (?, ?, ?)";

            PreparedStatement st = DatabaseConnection.getConnection().prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, role);
            st.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

    }
}
