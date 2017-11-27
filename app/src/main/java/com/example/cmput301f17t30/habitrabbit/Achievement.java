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

import android.graphics.Bitmap;

/**
 * Created by Adam on 26-Nov-17.
 */

public class Achievement {

    private Boolean completed;
    private Integer progress;
    private Integer progressRequired;
    private String description;
    private Bitmap bitmap;
    private String name;

    public Achievement(Integer required, String description, String name){
        this.completed = Boolean.FALSE;
        this.progress = 0;
        this.description = description;
        this.progressRequired = required;
        this.name = name;
    }

    public void setCompleted(){
        this.completed = Boolean.TRUE;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getProgressRequired() {
        return progressRequired;
    }

    public void setProgressRequired(Integer progressRequired) {
        this.progressRequired = progressRequired;
    }

    public void incrementProgress(){
        this.progress += 1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
