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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * arankin on 26/10/17.
 */

public class HabitLayoutAdapter extends RecyclerView.Adapter<HabitLayoutAdapter.ViewHolder>  {

    private ArrayList<Habit> habitList;
    private Context mainContext;


    public HabitLayoutAdapter(ArrayList<Habit> habitList, Context context) {
        this.habitList = habitList;
        this.mainContext = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView habitName;
        private TextView habitReason;
        private ImageView lightImage;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            habitName = itemView.findViewById(R.id.habitNameTextView);
            habitReason = itemView.findViewById(R.id.habitEventCommentTextView);
            lightImage = itemView.findViewById(R.id.trafficLightImage);
        }

        @Override
        public void onClick(View view) {

            final String EDIT_HABIT_NAME = "EditHabitName";
            final String  EDIT_HABIT_REASON = "EditHabitReason";
            final String EDIT_HABIT_DAYS = "EditHabitDays";
            final String EDIT_HABIT_POSITION = "EditHabitPosition";
            final String EDIT_HABIT_DATE = "EditHabitDate";



            Intent editHabit = new Intent(mainContext, EditHabitActivity.class);
            String name = habitList.get(getPosition()).getTitle();
            String reason = habitList.get(getPosition()).getReason();
            ArrayList<Boolean> days = habitList.get(getPosition()).getDays();
            Date date = habitList.get(getPosition()).getStartDate();

            editHabit.putExtra(EDIT_HABIT_NAME, name);
            editHabit.putExtra(EDIT_HABIT_REASON, reason);
            editHabit.putExtra(EDIT_HABIT_DAYS, days);
            editHabit.putExtra(EDIT_HABIT_POSITION, getPosition());
            editHabit.putExtra(EDIT_HABIT_DATE, date);


            ((Activity)mainContext).startActivityForResult(editHabit, 2);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .habit_row_layout, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(HabitLayoutAdapter.ViewHolder holder, final int position) {
        final Habit habit = habitList.get(position);
        String title = habit.getTitle();
        String reason = habit.getReason();
        Bitmap light;
        Date todayDate = new Date();
        Date lastCompleteDate = habit.getLastCompleted();


        Calendar calToday = Calendar.getInstance();
        Calendar calLast = Calendar.getInstance();
        calToday.setTime(todayDate);
        calLast.setTime(lastCompleteDate);
        Boolean sameDay = calToday.get(Calendar.YEAR) == calLast.get(Calendar.YEAR) &&
                calToday.get(Calendar.DAY_OF_YEAR) == calLast.get(Calendar.DAY_OF_YEAR);


        if (habit.isDueToday() && (sameDay == Boolean.TRUE) )
            light = BitmapFactory.decodeResource(mainContext.getResources(), R.drawable.greenlight);
        else if (habit.isDueToday() && (sameDay == Boolean.FALSE))
            light = BitmapFactory.decodeResource(mainContext.getResources(),R.drawable.redlight);
        else
            light = BitmapFactory.decodeResource(mainContext.getResources(),R.drawable.yellowlight);



        holder.habitName.setText(title);
        holder.habitReason.setText(reason);
        holder.lightImage.setImageBitmap(light);



        /*
        Button delete = holder.itemView.findViewById(R.id.deleteHabitButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do stuff
                notifyItemChanged(position);

            }
        });
        */

    }


    @Override
    public int getItemCount() {

        return habitList.size();
    }
}
