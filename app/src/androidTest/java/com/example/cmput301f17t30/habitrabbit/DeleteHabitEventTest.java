package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.example.cmput301f17t30.habitrabbit.Activities.HabitHistoryActivity;
import com.example.cmput301f17t30.habitrabbit.model.Habit;
import com.robotium.solo.Solo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;


import static com.example.cmput301f17t30.habitrabbit.Activities.MainActivity.eventController;
import static com.example.cmput301f17t30.habitrabbit.Activities.MainActivity.eventList;
import static com.example.cmput301f17t30.habitrabbit.Activities.MainActivity.habitController;

/**
 * Created by gaoxin on 2017/11/13.
 */

public class DeleteHabitEventTest extends ActivityInstrumentationTestCase2<HabitHistoryActivity> {
    private Solo solo;

    public DeleteHabitEventTest(){
        super(HabitHistoryActivity.class);

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
        solo = new Solo(getInstrumentation(), getActivity());
    }
    public void testViewEventActivity(){
        final TextView command = (TextView) solo.getView(R.id.habitEventCommentTextView);
        solo.clickOnView(command);
        solo.sleep(2000);
        solo.clickOnButton("DELETE");
        solo.sleep(2000);
        assertTrue(eventList.getSize() == 0);
    }


    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}
