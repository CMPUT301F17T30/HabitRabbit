package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by arankin on 10/18/17.
 */

public class TestFriend extends ActivityInstrumentationTestCase2 {

    public TestFriend() {
        super(Friend.class);
    }

    public void testSetId(){
        String ID = "bob";
        Friend friend = new Friend(ID);
        assertEquals(ID, friend.getId());
    }


    public void testGetId(){
        String ID = "bob";
        Friend friend = new Friend(ID);
        String newID = "tim";
        friend.setId(newID);
        assertEquals(newID, friend.getId());

    }
}
