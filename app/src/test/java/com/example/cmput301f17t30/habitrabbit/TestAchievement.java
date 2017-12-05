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


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for Achievement model class
 */

public class TestAchievement {

    public TestAchievement() {
        super();
    }

    @Test
    public void testSetCompleted(){
        Achievement achievement = new Achievement(1, "do a task","testAchievement");
        assertEquals(Boolean.FALSE,achievement.getCompleted());
        achievement.setCompleted();
        assertEquals(Boolean.TRUE,achievement.getCompleted());
    }

    @Test
    public void testGetProgress(){
        Achievement achievement = new Achievement(1, "do a task","testAchievement");
        assertEquals((Integer)0,achievement.getProgress());
    }

    @Test
    public void testSetProgress(){
        Achievement achievement = new Achievement(1, "do a task","testAchievement");
        achievement.setProgress(6);
        assertEquals((Integer)6,achievement.getProgress());
    }

    @Test
    public void testIncrementProgress(){
        Achievement achievement = new Achievement(1, "do a task","testAchievement");
        achievement.incrementProgress();
        assertEquals((Integer)1,achievement.getProgress());
        achievement.incrementProgress();
        assertEquals((Integer)2,achievement.getProgress());

    }

    @Test
    public void testGetProgressRequired(){
        Achievement achievement = new Achievement(1, "do a task","testAchievement");
        assertEquals((Integer)1,achievement.getProgressRequired());
    }

    @Test
    public void testGetDescription(){
        Achievement achievement = new Achievement(1, "do a task","testAchievement");
        assertEquals("do a task",achievement.getDescription());
    }

    @Test
    public void testSetDescription(){
        Achievement achievement = new Achievement(1, "do a task","testAchievement");
        achievement.setDescription("new description");
        assertEquals("new description",achievement.getDescription());
    }

    @Test
    public void testGetName(){
        Achievement achievement = new Achievement(1, "do a task","testAchievement");
        assertEquals("testAchievement", achievement.getName());
    }

    @Test
    public void testSetName(){
        Achievement achievement = new Achievement(1, "do a task","testAchivement");
        achievement.setName("new name");
        assertEquals("new name", achievement.getName());
    }
}
