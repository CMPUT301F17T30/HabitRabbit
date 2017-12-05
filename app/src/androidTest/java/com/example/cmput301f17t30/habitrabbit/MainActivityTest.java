package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
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
        //super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());

    }

    public void testAddHabit(){
        solo.assertCurrentActivity("Not MainActivity!", MainActivity.class);

        // Entering AddHabitActivity class
        solo.clickOnView((Button) solo.getView(R.id.addHabitButton));
        solo.assertCurrentActivity("Not AddHabit!", AddHabitActivity.class);

        // Fills in fields for new habit
        solo.enterText((EditText) solo.getView(R.id.addHabitName), "New Habit 1");
        solo.enterText((EditText) solo.getView(R.id.addHabitReason), "Comment 1");
        solo.clickOnView(solo.getView(R.id.addHabitStartDate));
        solo.typeText((EditText) solo.getView(R.id.addHabitStartDate), "01-01-2017");
        solo.clickOnView(solo.getView(R.id.addHabitMondayCheck));

        // Saves the habit and returns to main menu
        solo.clickOnView(solo.getView(R.id.addHabitDone));
        solo.assertCurrentActivity("Not MainActivity", MainActivity.class);

        // Checks that the text fields of the habit match the input
        assertTrue(habitList.getHabit(0).getTitle().equals("New Habit 1"));
        assertTrue(habitList.getHabit(0).getReason().equals("Comment 1"));

        Calendar dueTodayCal = new GregorianCalendar(2017, 10,13);
        Date dueDate = dueTodayCal.getTime();

        Calendar startDateCal = new GregorianCalendar(2017, 0, 1);
        Date startDate = startDateCal.getTime();

        // Checks that the dates of the habit match the input
        assertTrue(habitList.getHabit(0).isDueToday(dueDate) == Boolean.TRUE);
        assertTrue(habitList.getHabit(0).getStartDate().equals(startDate));

        habitList.deleteHabit(0);
    }

    public void testViewHabit(){
        solo.assertCurrentActivity("Not MainActivity!", MainActivity.class);

        // Entering AddHabitActivity class
        solo.clickOnView((Button) solo.getView(R.id.addHabitButton));
        solo.assertCurrentActivity("Not AddHabit!", AddHabitActivity.class);

        // Fills in fields for new habit
        solo.enterText((EditText) solo.getView(R.id.addHabitName), "New Habit 1");
        solo.enterText((EditText) solo.getView(R.id.addHabitReason), "Comment 1");
        solo.clickOnView(solo.getView(R.id.addHabitStartDate));
        solo.typeText((EditText) solo.getView(R.id.addHabitStartDate), "01-01-2017");
        solo.clickOnView(solo.getView(R.id.addHabitMondayCheck));

        // Saves the habit and returns to main menu
        solo.clickOnView(solo.getView(R.id.addHabitDone));

        solo.assertCurrentActivity("Not MainActivity!", MainActivity.class);
        solo.clickInRecyclerView(0);
        solo.assertCurrentActivity("Not ViewHabitDetailActivity!", ViewHabitDetailActivity.class);

        Habit habit = habitList.getHabit(0);



        assertTrue(((TextView) solo.getView(R.id.viewHabitTitle)).getText().equals(habit.getTitle()));
        assertTrue(((TextView) solo.getView(R.id.viewHabitReason)).getText().equals(habit.getReason()));

        int complete = Integer.parseInt(((TextView) solo.getView(R.id.viewHabitCompleted)).getText().toString());
        int failed = Integer.parseInt(((TextView) solo.getView(R.id.viewHabitFailed)).getText().toString());
        assertTrue(complete == habit.getTimesCompleted());
        assertTrue(failed == habit.getTimesFailed());

        habitList.deleteHabit(0);

    }

    public void testEditHabit(){
        solo.assertCurrentActivity("Not MainActivity!", MainActivity.class);

        // Entering AddHabitActivity class
        solo.clickOnView((Button) solo.getView(R.id.addHabitButton));
        solo.assertCurrentActivity("Not AddHabit!", AddHabitActivity.class);

        // Fills in fields for new habit
        solo.enterText((EditText) solo.getView(R.id.addHabitName), "New Habit 1");
        solo.enterText((EditText) solo.getView(R.id.addHabitReason), "Comment 1");
        solo.clickOnView(solo.getView(R.id.addHabitStartDate));
        solo.typeText((EditText) solo.getView(R.id.addHabitStartDate), "01-01-2017");
        solo.clickOnView(solo.getView(R.id.addHabitMondayCheck));

        // Saves the habit and returns to main menu
        solo.clickOnView(solo.getView(R.id.addHabitDone));
        solo.assertCurrentActivity("Not MainActivity", MainActivity.class);

        solo.clickInRecyclerView(0);
        solo.assertCurrentActivity("Not ViewHabitDetailActivity!", ViewHabitDetailActivity.class);

        solo.clickOnView(solo.getView(R.id.viewHabitEditButton));
        solo.assertCurrentActivity("Not EditHabitActivity!", EditHabitActivity.class);

        solo.clickOnView(solo.getView(R.id.editHabitName));
        solo.typeText((EditText) solo.getView(R.id.editHabitName), "Edited Habit 1");
        solo.clickOnView(solo.getView(R.id.editHabitReason));
        solo.typeText((EditText) solo.getView(R.id.editHabitReason), "Edited Comment 1");
        solo.clickOnView(solo.getView(R.id.editHabitStartDate));
        solo.typeText((EditText) solo.getView(R.id.editHabitStartDate), "01-01-2018");

        solo.clickOnView(solo.getView(R.id.editHabitDone));

        solo.waitForActivity(MainActivity.class);

        solo.assertCurrentActivity("Not MainActivity", MainActivity.class);

        // Checks that the text fields of the habit match the edited input
        assertTrue(habitList.getHabit(0).getTitle().equals("Edited Habit 1"));
        assertTrue(habitList.getHabit(0).getReason().equals("Edited Comment 1"));

        Calendar startDateCal = new GregorianCalendar(2018, 0, 1);
        Date startDate = startDateCal.getTime();

        // Checks that the dates of the habit match the edited input
        assertTrue(habitList.getHabit(0).getStartDate().equals(startDate));

        habitList.deleteHabit(0);
    }

    public void testDeleteHabit(){
        solo.assertCurrentActivity("Not MainActivity!", MainActivity.class);

        // Entering AddHabitActivity class
        solo.clickOnView((Button) solo.getView(R.id.addHabitButton));
        solo.assertCurrentActivity("Not AddHabit!", AddHabitActivity.class);

        // Fills in fields for new habit
        solo.enterText((EditText) solo.getView(R.id.addHabitName), "New Habit 1");
        solo.enterText((EditText) solo.getView(R.id.addHabitReason), "Comment 1");
        solo.clickOnView(solo.getView(R.id.addHabitStartDate));
        solo.typeText((EditText) solo.getView(R.id.addHabitStartDate), "01-01-2017");
        solo.clickOnView(solo.getView(R.id.addHabitMondayCheck));

        // Saves the habit and returns to main menu
        solo.clickOnView(solo.getView(R.id.addHabitDone));
        solo.assertCurrentActivity("Not MainActivity", MainActivity.class);

        solo.clickInRecyclerView(0);
        solo.assertCurrentActivity("Not ViewHabitDetailActivity!", ViewHabitDetailActivity.class);

        solo.clickOnView(solo.getView(R.id.viewHabitDeleteButton));

        solo.waitForActivity(MainActivity.class);

        assertTrue(habitList.getSize() == 0);

    }

    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }


}
