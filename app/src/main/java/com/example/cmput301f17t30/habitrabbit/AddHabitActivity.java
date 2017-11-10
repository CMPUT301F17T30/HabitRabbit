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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CheckBox;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;

public class AddHabitActivity extends AppCompatActivity {

    public static final String ADD_HABIT_NAME = "AddHabitName";
    public static final String  ADD_HABIT_REASON = "AddHabitReason";
    public static final String ADD_HABIT_DAYS = "AddHabitDays";

    ArrayList<Boolean> days = new ArrayList<>();

    private static final int MONDAY = 0;
    private static final int TUESDAY = 1;
    private static final int WEDNESDAY = 2;
    private static final int THURSDAY = 3;
    private static final int FRIDAY = 4;
    private static final int SATURDAY = 5;
    private static final int SUNDAY = 6;

    EditText name;
    EditText reason;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        for(int i = 0; i < 7; i++){
            days.add(FALSE);
        }

        name = (EditText) findViewById(R.id.addHabitName);
        reason = (EditText) findViewById(R.id.addHabitReason);



        CheckBox mondayButton = (CheckBox) findViewById(R.id.addHabitMondayCheck);
        CheckBox tuesdayButton = (CheckBox) findViewById(R.id.addHabitTuesdayCheck);
        CheckBox wednesdayButton = (CheckBox) findViewById(R.id.addHabitWednesdayCheck);
        CheckBox thursdayButton = (CheckBox) findViewById(R.id.addHabitThursdayCheck);
        CheckBox fridayButton = (CheckBox) findViewById(R.id.addHabitFridayCheck);
        CheckBox saturdayButton = (CheckBox) findViewById(R.id.addHabitSaturdayCheck);
        CheckBox sundayButton = (CheckBox) findViewById(R.id.addHabitSundayCheck);


        CompoundButton.OnCheckedChangeListener dayCheckListener = new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton day, boolean isChecked) {
                switch (day.getId()){
                    case R.id.addHabitMondayCheck:
                        days.set(MONDAY, isChecked);
                        break;

                    case R.id.addHabitTuesdayCheck:
                        days.set(TUESDAY, isChecked);
                        break;

                    case R.id.addHabitWednesdayCheck:
                        days.set(WEDNESDAY, isChecked);
                        break;

                    case R.id.addHabitThursdayCheck:
                        days.set(THURSDAY, isChecked);
                        break;

                    case R.id.addHabitFridayCheck:
                        days.set(FRIDAY, isChecked);
                        break;

                    case R.id.addHabitSaturdayCheck:
                        days.set(SATURDAY, isChecked);
                        break;

                    case R.id.addHabitSundayCheck:
                        days.set(SUNDAY, isChecked);
                        break;
                }
            }
        };


        mondayButton.setOnCheckedChangeListener(dayCheckListener);
        tuesdayButton.setOnCheckedChangeListener(dayCheckListener);
        wednesdayButton.setOnCheckedChangeListener(dayCheckListener);
        thursdayButton.setOnCheckedChangeListener(dayCheckListener);
        fridayButton.setOnCheckedChangeListener(dayCheckListener);
        saturdayButton.setOnCheckedChangeListener(dayCheckListener);
        sundayButton.setOnCheckedChangeListener(dayCheckListener);


        Button doneButton = (Button) findViewById(R.id.addHabitDone);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                if (name.getText().toString().isEmpty()){
                    name.setError("Habit name required");
                }

                else {
                    addHabitDone();
                }
            }

        });

    }

    public void addHabitDone(){
        try{
            Intent returnToMain = new Intent();

            String habitName = name.getText().toString();
            String habitReason = reason.getText().toString();

            returnToMain.putExtra(ADD_HABIT_NAME, habitName);
            returnToMain.putExtra(ADD_HABIT_REASON, habitReason);
            returnToMain.putExtra(ADD_HABIT_DAYS, days);

            setResult(RESULT_OK, returnToMain);

            finish();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
