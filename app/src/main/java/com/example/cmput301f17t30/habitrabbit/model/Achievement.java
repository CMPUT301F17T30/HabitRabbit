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

package com.example.cmput301f17t30.habitrabbit.model;

/**
 * Class to model an achievement and its details, and track progress towards completion
 */

public class Achievement {

    private Boolean completed;
    private Integer progress;
    private Integer progressRequired;
    private String description;
    private String name;

    /**
     * The achievement constructor
     *
     * @param required the number of times a task must bo completed to unlock the achievement
     * @param description a short description of how to unlock the achievement
     * @param name the title of the achievement
     */
    public Achievement(Integer required, String description, String name){
        this.completed = Boolean.FALSE;
        this.progress = 0;
        this.description = description;
        this.progressRequired = required;
        this.name = name;
    }

    /**
     * marks the achievement as unlocked
     */
    public void setCompleted(){
        this.completed = Boolean.TRUE;
    }

    /**
     * @return whether or not the user has unlocked this achievement
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * @return the progress towards unlocking the achievement
     */
    public Integer getProgress() {
        return progress;
    }

    /**
     * @param progress the progress towards unlocking this achievement
     */
    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    /**
     * @return the number of times a task must be completed to unlock this achievement
     */
    public Integer getProgressRequired() {
        return progressRequired;
    }

    /**\
     *
     * @param progressRequired the number of times a task must be completed to unlock this achievement
     */
    public void setProgressRequired(Integer progressRequired) {
        this.progressRequired = progressRequired;
    }

    /**
     *  increases the achievement progress by 1
     */
    public void incrementProgress(){
        this.progress += 1;
    }

    /**
     * @return a short description of how to unlock the achievement
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description a short description of how to unlock the achievement
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the title of this achievement
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the title of this achievement
     */
    public void setName(String name) {
        this.name = name;
    }
}
