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

package com.example.cmput301f17t30.habitrabbit.Utils;

/**
 * sets a flag and a listener to notify when elastic search is done pulling
 */

public class elasticDoneBoolean {
    private boolean elasticDone = false;
    private ChangeListener listener;

    public boolean isDone() {
        return elasticDone;
    }

    public void setDone(boolean done) {
        this.elasticDone = done;
        if (listener != null) {
            listener.onChange();
        }
    }

    public void reset() {
        elasticDone = false;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}
