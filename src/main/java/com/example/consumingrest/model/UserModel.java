package com.example.consumingrest.model;

import com.example.consumingrest.DatabaseConnection;
import com.example.consumingrest.data.User;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserModel {

    static public User getUser(String username) throws Exception {
        User user = null;

        try {
            ResultSet resultSet = DatabaseConnection.doQuery(
                    "SELECT * FROM `USER` WHERE `username` = ?",
                    new ArrayList<String>() {{add(username);}}
            );

            // check if empty
            if (!resultSet.next()) {
                throw new Exception();
            }

            user = new User(resultSet.getLong("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("role")
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }

        return user;
    }

    static public Long getUserId(String username) throws Exception{
        try {
            ResultSet resultSet = DatabaseConnection.doQuery(
                    "SELECT * FROM `USER` WHERE `username` = ?",
                    new ArrayList<String>() {{add(username);}}
            );

            // check if empty
            if (!resultSet.next()) {
                throw new Exception();
            }

            return resultSet.getLong("id");

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
}
