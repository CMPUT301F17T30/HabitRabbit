package com.example.cmput301f17t30.habitrabbit;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;

/**
 * Created by gaoxin on 2017/11/29.
 */

public class EventMapTest  extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public EventMapTest() {
        super(com.example.cmput301f17t30.habitrabbit.MainActivity.class);
        ArrayList<Boolean> days = new ArrayList<Boolean>(Arrays.asList(new Boolean[7]));
        Collections.fill(days, Boolean.TRUE);
        //Habit testHabit = new Habit();
        habitController.addHabit("test title", days, new Date());
        habitController.saveAddHabit();
        //assertTrue(habitList.getHabit(1) == testHabit);
    }


    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testEventMap(){
        Activity activity = getActivity();
        //solo.clickOnView(solo.getView(R.id.buttonAddHabitEvent));
        //solo.clickOnButton(R.id.addHabitButton);
        //solo.assertCurrentActivity("Wrong activity", AddHabitActivity.class);

        solo.clickOnButton("ADD EVENT");
        solo.assertCurrentActivity("Wrong activity", AddEventActivity.class);

        solo.enterText((EditText) solo.getView(R.id.comment), "testcommand");
        assertTrue(solo.waitForText("testcommand"));

        solo.clickOnView(solo.getView(R.id.gps));

        solo.clickOnView(solo.getView(R.id.save_event));

        solo.clickOnView(solo.getView(R.id.habitHistoryButton));
        solo.sleep(2000);
        solo.clickOnView(solo.getView(R.id.map));
        solo.sleep(2000);
        solo.clickOnView(solo.getView(R.id.highlight));
        solo.sleep(2000);
        solo.clickOnView(solo.getView(R.id.highlight));
        solo.sleep(2000);

    }
    public void tearDown() throws Exception{
        super.tearDown();
        solo.finishOpenedActivities();
    }

}