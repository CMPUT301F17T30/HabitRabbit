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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class is used to display event detail.
 */

public class ViewEventDetail extends AppCompatActivity {

    //build fake info
    ArrayList<Boolean> daylist = new ArrayList<Boolean>();
    Habit habit = new Habit("title 1","test",daylist, new Date());
    HabitEvent event = new HabitEvent(habit);

    private Bitmap selectImage;

    private ImageView eventImage;
    private TextView commentText;
    private TextView locatoinText;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_detail);


        //set fake habit event info
        event.setComment("gg simida");
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        selectImage = BitmapFactory.decodeFile("/storage/emulated/0/Download/e26hoMx-.jpeg", o2);
        event.setLocation("nihao,womenshiyaojing");
        event.setImage(selectImage);



        commentText = (TextView) findViewById(R.id.command_text);
        locatoinText = (TextView) findViewById(R.id.locatoin_text);
        eventImage = (ImageView) findViewById(R.id.ivImage);
        final Button editButton = (Button) findViewById(R.id.edit);

        //display fake info
        commentText.setText(event.getComment());
        locatoinText.setText(event.getLocation());
        eventImage.setImageBitmap(event.getImage());



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*intent = new Intent(ViewEventDetail.this, EditEvent.class);
                startActivity(intent);*/
            }
        });

    }
}
