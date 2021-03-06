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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitController;

/**
 * The recyclerview adapter that works with mainactivity to display the list of habits.
 *
 * @see com.example.cmput301f17t30.habitrabbit.MainActivity
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
        private ImageView lightImage;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            habitName = itemView.findViewById(R.id.habitNameTextView);
            lightImage = itemView.findViewById(R.id.trafficLightImage);
        }

        @Override
        public void onClick(View view) {
            Intent viewHabit = new Intent(mainContext, ViewHabitDetailActivity.class);
            habitController.viewHabit(getAdapterPosition());
            ((Activity)mainContext).startActivityForResult(viewHabit, MainActivity.VIEW_HABIT_REQUEST);
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
        Bitmap light;
        Boolean sameDay;
        sameDay = habit.isSameDay();

        //set the appropriate light indicator
        if (habit.isDueToday() && (sameDay == Boolean.TRUE) )
            light = BitmapFactory.decodeResource(mainContext.getResources(), R.drawable.greenlight);
        else if (habit.isDueToday() && (sameDay == Boolean.FALSE))
            light = BitmapFactory.decodeResource(mainContext.getResources(),R.drawable.redlight);
        else
            light = BitmapFactory.decodeResource(mainContext.getResources(),R.drawable.yellowlight);

        holder.habitName.setText(title);
        holder.lightImage.setImageBitmap(light);
        Button addEventButton = holder.itemView.findViewById(R.id.buttonAddHabitEvent);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newEvent = new Intent(mainContext, AddEventActivity.class);
                newEvent.putExtra("pos", position);
                mainContext.startActivity(newEvent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }

}
