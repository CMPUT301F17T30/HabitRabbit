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

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class EditHabitActivity extends AppCompatActivity {

    public static final String EDIT_HABIT_NAME = "EditHabitName";
    public static final String  EDIT_HABIT_REASON = "EditHabitReason";
    public static final String EDIT_HABIT_DAYS = "EditHabitDays";
    public static final String EDIT_HABIT_POSITION = "EditHabitPosition";
    public static final String EDIT_HABIT_DATE = "EditHabitDate";


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

    SimpleDateFormat format;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);

        days = (ArrayList<Boolean>) getIntent().getSerializableExtra(EDIT_HABIT_DAYS);
        String oldName = getIntent().getStringExtra(EDIT_HABIT_NAME);
        String oldReason = getIntent().getStringExtra(EDIT_HABIT_REASON);
        Date oldDate = (Date) getIntent().getSerializableExtra(EDIT_HABIT_DATE);


        name = (EditText) findViewById(R.id.editHabitName);
        reason = (EditText) findViewById(R.id.editHabitReason);
        date = (EditText) findViewById(R.id.editHabitStartDate);

        format = new SimpleDateFormat("dd-MM-yyyy");
        format.setLenient(FALSE);

        String startDate = format.format(oldDate);

        name.setText(oldName);
        reason.setText(oldReason);
        date.setText(startDate);




        CheckBox mondayButton = (CheckBox) findViewById(R.id.editHabitMondayCheck);
        CheckBox tuesdayButton = (CheckBox) findViewById(R.id.editHabitTuesdayCheck);
        CheckBox wednesdayButton = (CheckBox) findViewById(R.id.editHabitWednesdayCheck);
        CheckBox thursdayButton = (CheckBox) findViewById(R.id.editHabitThursdayCheck);
        CheckBox fridayButton = (CheckBox) findViewById(R.id.editHabitFridayCheck);
        CheckBox saturdayButton = (CheckBox) findViewById(R.id.editHabitSaturdayCheck);
        CheckBox sundayButton = (CheckBox) findViewById(R.id.editHabitSundayCheck);

        mondayButton.setChecked(days.get(MONDAY));
        tuesdayButton.setChecked(days.get(TUESDAY));
        wednesdayButton.setChecked(days.get(WEDNESDAY));
        thursdayButton.setChecked(days.get(THURSDAY));
        fridayButton.setChecked(days.get(FRIDAY));
        saturdayButton.setChecked(days.get(SATURDAY));
        sundayButton.setChecked(days.get(SUNDAY));


        CompoundButton.OnCheckedChangeListener dayCheckListener = new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton day, boolean isChecked) {
                switch (day.getId()){
                    case R.id.editHabitMondayCheck:
                        days.set(MONDAY, isChecked);
                        break;

                    case R.id.editHabitTuesdayCheck:
                        days.set(TUESDAY, isChecked);
                        break;

                    case R.id.editHabitWednesdayCheck:
                        days.set(WEDNESDAY, isChecked);
                        break;

                    case R.id.editHabitThursdayCheck:
                        days.set(THURSDAY, isChecked);
                        break;

                    case R.id.editHabitFridayCheck:
                        days.set(FRIDAY, isChecked);
                        break;

                    case R.id.editHabitSaturdayCheck:
                        days.set(SATURDAY, isChecked);
                        break;

                    case R.id.editHabitSundayCheck:
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


        Button doneButton = (Button) findViewById(R.id.editHabitDone);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Boolean dateFormat = FALSE;

                try {
                    Date startDate = format.parse(date.getText().toString());
                    if (startDate.after(new Date())) {
                        dateFormat = TRUE;
                    }
                    else {
                        date.setError("Date must be after current date");
                    }
                }

                catch (Exception e){
                    date.setError("Valid date required");
                }

                if (name.getText().toString().isEmpty()){
                    name.setError("Habit name required");
                }
                if (date.getText().toString().isEmpty()){
                    date.setError("Valid date required");
                }

                else if (dateFormat == TRUE){
                    editHabitDone();
                }
            }

        });

    }

    public void editHabitDone(){
        try{
            Intent returnToMain = new Intent();

            String habitName = name.getText().toString();
            String habitReason = reason.getText().toString();
            int position = getIntent().getIntExtra(EDIT_HABIT_POSITION, 0);
            Date startDate = format.parse(date.getText().toString());

            returnToMain.putExtra(EDIT_HABIT_NAME, habitName);
            returnToMain.putExtra(EDIT_HABIT_REASON, habitReason);
            returnToMain.putExtra(EDIT_HABIT_DAYS, days);
            returnToMain.putExtra(EDIT_HABIT_POSITION, position);
            returnToMain.putExtra(EDIT_HABIT_DATE, startDate);


            setResult(RESULT_OK, returnToMain);

            finish();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
