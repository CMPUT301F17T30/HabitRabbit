package com.example.cmput301f17t30.habitrabbit;

import io.searchbox.annotations.JestId;

/**
 * Created by Irissama on 2017-12-01.
 */

public class FriendRequest {
    private String reciverID;
    private String requestorID;

    @JestId
    private String id;

    private int status;
}
