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

package com.example.cmput301f17t30.habitrabbit.Controllers;

import android.util.Log;

import com.example.cmput301f17t30.habitrabbit.Commands.Command;
import com.example.cmput301f17t30.habitrabbit.Utils.NetWorkCheck;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A first in first out queue that continues trying to push the oldest command to elasticsearch
 * will check for internet access, and push when it has access
 */

public class CommandQueue {
    private Queue<Command> commandQueue = new LinkedBlockingQueue<>();

    public CommandQueue(){
    }

    public void runCommands(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    executeHead();
                }
            }
        }).start();

    }

    /**
     * @return the command at the head of the queue
     */
    public Command checkHead(){
        return commandQueue.peek();
    }

    /**
     * @param command the command the be added to the tail of the queue
     */
    public void addTail(Command command){
        commandQueue.offer(command);
    }

    /**
     * executes the first command in the queue if there is internet access
     */
    public void executeHead(){
        if (NetWorkCheck.isOnline() && (commandQueue.peek() != null)){
            commandQueue.poll().execute();
            Log.d("check", "executed Command");
        }
        else {
            //Log.d("no","no internet, command not executed");
        }
    }




}
