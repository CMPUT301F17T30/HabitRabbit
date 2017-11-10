package com.example.cmput301f17t30.habitrabbit;

/**
 * Created by arankin on 20/10/17.
 */

public class User {
    private  String userId;

    /**
     * @param userId A unique identifier for the user
     */
    public  User(String userId) {
        this.userId = userId;
    }

    /**
     * @return The unique identifier for the user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId A unique identifier for the user
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
