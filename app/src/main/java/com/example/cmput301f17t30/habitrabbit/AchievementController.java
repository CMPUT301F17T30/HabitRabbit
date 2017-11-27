/*
 *     <HabitRabbit- A habit tracking app.>
 *     Copyright (C) <2017>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.cmput301f17t30.habitrabbit;

import java.util.ArrayList;

/**
 * Sets achivements as complete, and loads achievment status
 */

public class AchievementController {
    private ArrayList<Achievement> achievementList = new ArrayList<>();
    private ArrayList<Boolean> isCompletedList;
    private Achievement weekendWarriorAchievement = new Achievement(10,"Complete 10 habits on a weekend", "Weekend Warrior");
    private Achievement busyAchievement = new Achievement(1,"complete 3 habits in one day","busy beaver");

    public AchievementController(){
        //set to True or False
        achievementList.add(weekendWarriorAchievement);
        achievementList.add(busyAchievement);
    }

    public ArrayList<Achievement> getAchievements() {
        return this.achievementList;
    }

    public void loadAchievementsStatus(){
        //get achivement status for each achievement here
        //call this on user login
    }

    public void saveAchievementsStatus(){
        //call this after any achievement is completed
        //upload status of elasticsearch
    }

    public void updateWeekendWarrior(){
        weekendWarriorAchievement.incrementProgress();
        if ((weekendWarriorAchievement.getCompleted() == Boolean.FALSE) &&
                (weekendWarriorAchievement.getProgress() >= weekendWarriorAchievement.getProgressRequired())) {
            weekendWarriorAchievement.setCompleted();
        }

    }


}
