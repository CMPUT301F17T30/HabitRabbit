package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by resplendent_lord_of_conquest on 08/11/17.
 */

public class MockHabit extends Habit {
    public String Title;
    public String Reason;
    public ArrayList<Boolean> days;
    public Integer timesFailed;
    public Integer timesCompleted;



    public MockHabit(){
        super("bob", new ArrayList<Boolean>());

        Title = "Testhabit";
        Reason = "for testing";
        this.days = new ArrayList<Boolean>(){{
            add(Boolean.TRUE);
            add(Boolean.FALSE);
            add(Boolean.FALSE);
            add(Boolean.TRUE);
            add(Boolean.FALSE);
            add(Boolean.FALSE);
            add(Boolean.FALSE);
        }};
        timesCompleted = 5;
        timesFailed =5;
    }
}
