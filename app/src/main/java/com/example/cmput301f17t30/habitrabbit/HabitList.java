

package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Created by Jacqueline on 2017-11-11.
 */

public class HabitList {
    private ArrayList<Habit> habitList;

    public HabitList(){
        habitList = new ArrayList<Habit>();
    }

    public void addHabit(Habit habit){
        habitList.add(habit);
    }

    public void editEvent(int position, Habit habit){
        habitList.set(position, habit);
    }

    public void deleteHabit(int position){
        habitList.remove(position);
    }

    public Habit getHabit(int position){
        return habitList.get(position);
    }

}
