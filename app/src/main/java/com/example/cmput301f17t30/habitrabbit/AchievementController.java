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

/**
 * Sets achivements as complete, and loads achievment status
 */

public class AchievementController {

    private Boolean achievementa;

    //complete a habit on a Saturday or Sunday 10 times
    private Boolean weekendWarrior;
    private Integer weekendWarriorProgress;

    private AchievementController(){
    }

    private void loadAchievementsStatus(){
        //get achivement status for each achievement here
        //call this on user login
    }

    private void saveAchievementsStatus(){
        //call this after any achievement is completed
        //upload status of elasticsearch
    }

    private Boolean isAchievementaComplete(){
        return this.achievementa;
    }

    private void setAchievementaComplete(){
        this.achievementa = Boolean.TRUE;
    }

    private void updateWeekendWarrior(){
        this.weekendWarriorProgress +=1;
    }

    private void checkWeekendWarriorComplete(){
        if (this.weekendWarriorProgress == 10){
            this.weekendWarrior = Boolean.TRUE;
        }
    }

    private Boolean isWeekendWarriorComplete(){
        return this.weekendWarrior;
    }


}
