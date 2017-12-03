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
 * Created by Adam on 03-Dec-17.
 */

public class UserDoneBoolean {
    private boolean userDone;
    private ChangeUserListener userListener;


    public boolean isUserDone() {
        return userDone;
    }

    public void setUserDone(Boolean done) {
        this.userDone = done;
        if (userListener != null) userListener.onChange();
    }

    public ChangeUserListener getUserListener() {
        return userListener;
    }

    public void setUserListener(ChangeUserListener listener) {
        this.userListener = listener;
    }

    public interface ChangeUserListener {
        void onChange();
    }
}
