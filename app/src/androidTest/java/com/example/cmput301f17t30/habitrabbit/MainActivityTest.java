package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitList;


/**
 * Created by Jacqueline on 2017-11-13.
 */

public class MainActivityTest extends ActivityInstrumentationTestCase2 {
    private Solo solo;

    public MainActivityTest(){
        super(com.example.cmput301f17t30.habitrabbit.MainActivity.class);
    }

    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());

    }

    public void testAddHabit(){
        solo.assertCurrentActivity("Not MainActivity!", MainActivity.class);
        solo.clickOnView((Button) solo.getView(R.id.addHabitButton));
        solo.assertCurrentActivity("Not AddHabit!", AddHabitActivity.class);
        solo.enterText((EditText) solo.getView(R.id.addHabitName), "New Habit 1");
        solo.enterText((EditText) solo.getView(R.id.addHabitReason), "Comment 1");
        solo.clickOnView(solo.getView(R.id.addHabitStartDate));
        solo.typeText((EditText) solo.getView(R.id.addHabitStartDate), "01-01-2017");
        solo.clickOnView(solo.getView(R.id.addHabitMondayCheck));

        solo.clickOnView(solo.getView(R.id.addHabitDone));

        solo.assertCurrentActivity("Not MainActivity", MainActivity.class);

        assertTrue(habitList.getHabit(0).getTitle().equals("New Habit 1"));
        assertTrue(habitList.getHabit(0).getReason().equals("Comment 1"));

        Calendar myCalendar = new GregorianCalendar(2017, 10,13);
        Date testDate = myCalendar.getTime();


        assertTrue(habitList.getHabit(0).isDueToday(testDate) == Boolean.TRUE);
       // assertTrue(habitList.getHabit(0).getStartDate().equals(new Date(2017, 11, 13)));
    }

}
