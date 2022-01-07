package com.example.consumingrest.model;

import com.example.consumingrest.DatabaseConnection;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AuthorizationModel {

    static private String getRessourcePermission(String ressource) throws Exception{
        try {
            ResultSet resultSet = DatabaseConnection.doQuery(
                    "SELECT * FROM `PERMISSION` WHERE `ressource` = ?",
                    new ArrayList<String>() {{add(ressource);}}
            );

            // check if exist
            if (!resultSet.next()) {
                throw new Exception();
            } else {
                return resultSet.getString("permission");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }

    static public Boolean isPermissionValid(String ressource, String token) {

        try {
            String permission = getRessourcePermission(ressource);
            Jws<Claims> jwt = JwtModel.parseJWT(token);
            String role = jwt.getBody().get("role", String.class);

            if (role.equals(permission) || role.equals("admin")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

}
