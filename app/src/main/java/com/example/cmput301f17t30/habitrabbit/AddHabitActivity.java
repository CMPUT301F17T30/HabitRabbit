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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class AddHabitActivity extends AppCompatActivity {

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
    EditText date;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        for(int i = 0; i < 7; i++){
            days.add(FALSE);
        }

        name = (EditText) findViewById(R.id.addHabitName);
        reason = (EditText) findViewById(R.id.addHabitReason);
        date = (EditText) findViewById(R.id.addHabitStartDate);


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

                try {
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    format.setLenient(FALSE);
                    Date startDate = format.parse(date.getText().toString());
                }

                catch (Exception e){
                    date.setError("Valid date required");
                }

                if (name.getText().toString().trim().isEmpty()){
                    name.setError("Habit name required");
                }
                else if (name.getText().toString().length() > 20){
                    name.setError("Habit name too long");
                }
                else if (reason.getText().toString().length() > 30){
                    reason.setError("Reason text too long");
                }
                else if (date.getText().toString().isEmpty()){
                    date.setError("Valid date required");
                }

                else {
                    addHabitDone();
                }
            }

        });

    }

    public void addHabitDone(){
        try{

            String habitName = name.getText().toString();
            String habitReason = reason.getText().toString();

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            format.setLenient(FALSE);
            Date startDate = format.parse(date.getText().toString());

            habitController.addHabit(habitName, days, startDate);

            if (!habitReason.trim().isEmpty()){
                habitController.setReason(habitReason);
            }


            Intent returnToMain = new Intent();
            setResult(RESULT_OK, returnToMain);

            finish();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
