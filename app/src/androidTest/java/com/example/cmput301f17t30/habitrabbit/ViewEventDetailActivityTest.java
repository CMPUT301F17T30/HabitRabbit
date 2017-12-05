package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;


/**
 * Created by gaoxin on 2017/11/13.
 */

public class ViewEventDetailActivityTest extends ActivityInstrumentationTestCase2<HabitHistoryActivity> {
    private Solo solo;

    public ViewEventDetailActivityTest(){
        super(com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity.class);

        ArrayList<Boolean> days = new ArrayList<>(Arrays.asList(new Boolean[7]));
        Collections.fill(days, Boolean.TRUE);
        Habit testHabit = new Habit("test title", days, new Date());
        habitController.addHabit("test title", days, new Date());
        habitController.saveAddHabit();

        eventController.addEvent(testHabit);
        eventController.setComment("test comment");
        eventController.saveAddEvent();
        //assertTrue(habitList.getHabit(1) == testHabit);
    }

    public void setUp()throws Exception{
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }
    public void testViewEventActivity(){
        final TextView command = (TextView) solo.getView(R.id.habitEventCommentTextView);
        solo.clickOnView(command);
        solo.sleep(2000);
        solo.clickOnView(solo.getView(R.id.editEventButton));
        solo.assertCurrentActivity("Wrong activity", EditEventActivity.class);
        solo.enterText((EditText) solo.getView(R.id.comment), "");
        solo.enterText((EditText) solo.getView(R.id.comment), "testcommand 2");


        solo.enterText((EditText)solo.getView(R.id.enter_location), "CSC");
        solo.clickOnView(solo.getView(R.id.search_location));

        solo.clickInList(0);
        solo.clickOnView(solo.getView(R.id.save_event));

        assertTrue(habitController.getTitle().equals("test title"));
    }

    public void tearDown() throws Exception{
        super.tearDown();
        solo.finishOpenedActivities();
    }
}
