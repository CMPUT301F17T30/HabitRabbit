package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.CheckBox;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.TestCase;


/**
 * Created by Jacqueline on 2017-11-13.
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2 {
    private Solo solo;

    public MainActivityTest(){
        super(com.example.cmput301f17t30.habitrabbit.AddHabitActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());

    }

    public void testAddHabit(){
        solo.assertCurrentActivity("Not MainActivity!", MainActivity.class);
        solo.clickOnButton(R.id.addHabitButton);
        solo.assertCurrentActivity("Not AddHabit!", AddHabitActivity.class);
        solo.enterText((EditText) solo.getView(R.id.addHabitName), "New Habit 1");
        solo.enterText((EditText) solo.getView(R.id.addHabitReason), "Comment 1");
        solo.enterText((EditText) solo.getView(R.id.addHabitStartDate), "01-01-2018");
        solo.clickOnCheckBox(R.id.addHabitMondayCheck);

        solo.clickOnButton(R.id.addHabitDone);

        assertTrue(solo.waitForText("New Habit 1"));

    }

}
