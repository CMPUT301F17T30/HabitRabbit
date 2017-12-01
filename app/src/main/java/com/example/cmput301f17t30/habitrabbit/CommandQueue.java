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

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Adam on 30-Nov-17.
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

    public Command checkHead(){
        return commandQueue.peek();
    }

    public void addTail(Command command){
        commandQueue.offer(command);
    }

    public void executeHead(){
        if (NetWorkCheck.isOnline() && (commandQueue.peek() != null)){
            commandQueue.poll().execute();
        }
    }




}
