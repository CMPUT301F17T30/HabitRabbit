package com.example.cmput301f17t30.habitrabbit;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Solo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity.habitEventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitList;

/**
 * Created by gaoxin on 2017/11/13.
 */

public class DeleteHabitEventTest extends ActivityInstrumentationTestCase2<HabitHistoryActivity> {
    private Solo solo;

    public DeleteHabitEventTest(){
        super(com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity.class);

        ArrayList<Boolean> days = new ArrayList<Boolean>(Arrays.asList(new Boolean[7]));
        Collections.fill(days, Boolean.TRUE);
        Habit testHabit = new Habit("test title", days, new Date());
        habitController.addHabit("test title", days, new Date());
        habitController.saveAddHabit();

        habitEventController.addEvent(testHabit);
        habitEventController.setComment("test comment");
        habitEventController.saveAddEvent();
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
