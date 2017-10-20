package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by arankin on 20/10/17.
 */

public class User {
    public String userId;

    public User(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
