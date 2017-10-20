package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by arankin on 20/10/17.
 */

public class TestUser extends ActivityInstrumentationTestCase2 {

    public TestUser(){
        super (User.class);
    }

    public void testGetId(){
        User user = new User("bob");
        assertEquals("bob",user.getUserId());
    }

    public void testSetId(){
        User user = new User("bob");
        user.setUserId("timmy");
        assertEquals("timmy",user.getUserId());
    }
}
