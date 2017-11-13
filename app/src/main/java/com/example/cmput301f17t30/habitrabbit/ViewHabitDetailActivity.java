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
 * This activity is used to display a single habit object in detail.
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;
import static java.lang.Math.floor;

public class ViewHabitDetailActivity extends AppCompatActivity {

    TextView title;
    TextView reason;

    TextView percent;
    TextView failed;
    TextView completed;

    ImageView grade;

    int picture_id;

    Bitmap gradePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_habit_detail);

        title = (TextView) findViewById(R.id.viewHabitTitle);
        reason = (TextView) findViewById(R.id.viewHabitReason);
        percent = (TextView) findViewById(R.id.viewHabitPercent);
        failed = (TextView) findViewById(R.id.viewHabitFailed);
        completed = (TextView) findViewById(R.id.viewHabitCompleted);
        grade = (ImageView) findViewById(R.id.viewHabitGrade);


        String habitTitle = habitController.getTitle();
        String habitReason = habitController.getReason();
        double habitPercent = habitController.getPercentageCompletion();
        String percentText = Double.toString(floor(habitPercent*100)) + "%";
        int complete = habitController.getCompleted();
        int fail = habitController.getFailed();
        String completeText = Integer.toString(complete);
        String failText = Integer.toString(fail);

        if (habitPercent <= 0.15){
            picture_id = R.drawable.gradefminus;
        }
        else if (habitPercent <= 0.30){
            picture_id = R.drawable.gradef;
        }
        else if (habitPercent <= 0.45){
            picture_id = R.drawable.graded;
        }
        else if (habitPercent <= 0.60){
            picture_id = R.drawable.gradec;
        }
        else if (habitPercent <= 0.75){
            picture_id = R.drawable.gradeb;
        }
        else if (habitPercent <= 0.90){
            picture_id = R.drawable.gradea;
        }
        else {
            picture_id = R.drawable.gradeaplus;
        }

        gradePicture = BitmapFactory.decodeResource(getResources(), picture_id);

        title.setText(habitTitle);
        reason.setText(habitReason);
        percent.setText(percentText);
        failed.setText(failText);
        completed.setText(completeText);
        grade.setImageBitmap(gradePicture);

        Button editHabit = (Button) findViewById(R.id.viewHabitEditButton);
        editHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editHabit = new Intent(ViewHabitDetailActivity.this, EditHabitActivity.class);
                startActivityForResult(editHabit, 0);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 0){
            if (resultCode == RESULT_OK){
                Intent returnToMain = new Intent();
                setResult(RESULT_OK, returnToMain);
                finish();

            }
        }

    }


}
