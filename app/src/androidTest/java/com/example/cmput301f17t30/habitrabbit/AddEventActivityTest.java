package com.example.cmput301f17t30.habitrabbit;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Solo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitList;

/**
 * Created by gaoxin on 2017/11/13.
 */

public class AddEventActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{
    private Solo solo;

    public AddEventActivityTest(){
        super(com.example.cmput301f17t30.habitrabbit.MainActivity.class);
        ArrayList<Boolean> days = new ArrayList<>(Arrays.asList(new Boolean[7]));
        Collections.fill(days, Boolean.TRUE);
        //Habit testHabit = new Habit();
        habitController.addHabit("test title", days, new Date());
        habitController.saveAddHabit();
        //assertTrue(habitList.getHabit(1) == testHabit);
    }


    public void setUp()throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }


    public void testAddEventActivity(){
        Activity activity = getActivity();
        //solo.clickOnView(solo.getView(R.id.buttonAddHabitEvent));
        //solo.clickOnButton(R.id.addHabitButton);
        //solo.assertCurrentActivity("Wrong activity", AddHabitActivity.class);

        solo.clickOnButton("ADD EVENT");
        solo.assertCurrentActivity("Wrong activity", AddEventActivity.class);

        solo.enterText((EditText) solo.getView(R.id.comment), "testcommand");
        assertTrue(solo.waitForText("testcommand"));

        solo.enterText((EditText)solo.getView(R.id.enter_location), "university of alberta");
        solo.clickOnView(solo.getView(R.id.search_location));

        solo.clickInList(0);
        solo.clickOnView(solo.getView(R.id.save_event));

        solo.sleep(2000);
        assertTrue(habitController.getTitle().equals("test title"));

        assertTrue(eventController.getComment(0).equals("testcommand"));

    }


    /*public void testAddEventActivity(){
        solo.assertCurrentActivity("Wrong activity", AddEventActivity.class);
        solo.enterText((EditText)solo.getView(R.id.comment), "testcommand");

        solo.enterText((EditText)solo.getView(R.id.enter_location), "university of alberta");
        solo.clickOnButton(R.id.enter_location);

        solo.clickInList(0);

        solo.clickOnButton(R.id.save_event);
        //assertTrue();
    }*/

    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }



}
