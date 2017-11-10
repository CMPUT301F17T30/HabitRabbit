package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by arankin on 20/10/17.
 */

public class User {
    private  String userId;

    private  User(String userId) {
        this.userId = userId;
    }

    private String getUserId() {
        return userId;
    }

    private void setUserId(String userId) {
        this.userId = userId;
    }
}
