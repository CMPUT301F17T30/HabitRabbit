package com.example.cmput301f17t30.habitrabbit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

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



        RadioButton mondayButton = (RadioButton) findViewById(R.id.mondayCheck);
        RadioButton tuesdayButton = (RadioButton) findViewById(R.id.tuesdayCheck);
        RadioButton wednesdayButton = (RadioButton) findViewById(R.id.wednesdayCheck);
        RadioButton thursdayButton = (RadioButton) findViewById(R.id.thursdayCheck);
        RadioButton fridayButton = (RadioButton) findViewById(R.id.fridayCheck);
        RadioButton saturdayButton = (RadioButton) findViewById(R.id.saturdayCheck);
        RadioButton sundayButton = (RadioButton) findViewById(R.id.sundayCheck);


        CompoundButton.OnCheckedChangeListener dayCheckListener = new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton day, boolean isChecked) {
                switch (day.getId()){
                    case R.id.mondayCheck:
                        days.set(MONDAY, isChecked);
                        break;

                    case R.id.tuesdayCheck:
                        days.set(TUESDAY, isChecked);
                        break;

                    case R.id.wednesdayCheck:
                        days.set(WEDNESDAY, isChecked);
                        break;

                    case R.id.thursdayCheck:
                        days.set(THURSDAY, isChecked);
                        break;

                    case R.id.fridayCheck:
                        days.set(FRIDAY, isChecked);
                        break;

                    case R.id.saturdayCheck:
                        days.set(SATURDAY, isChecked);
                        break;

                    case R.id.sundayCheck:
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
