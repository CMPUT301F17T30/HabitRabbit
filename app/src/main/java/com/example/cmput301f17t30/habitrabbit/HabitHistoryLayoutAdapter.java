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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity.habitEventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;

/**
 * Created by arankin on 11/2/17.
 */

public class HabitHistoryLayoutAdapter extends RecyclerView.Adapter<HabitHistoryLayoutAdapter.ViewHolder>{

    private ArrayList<HabitEvent> habitHistoryList;
    private Context historyContext;

    public HabitHistoryLayoutAdapter(ArrayList<HabitEvent> habitHistoryList, Context context) {
        this.habitHistoryList = habitHistoryList;
        this.historyContext = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView eventLocation;
        private TextView eventComment;
        private TextView eventType;


        public ViewHolder(View itemView) {
            super(itemView);

            eventLocation = itemView.findViewById(R.id.habitEventLocationTextView);
            eventComment = itemView.findViewById(R.id.habitEventCommentTextView);
            eventType = itemView.findViewById(R.id.eventTypeTextView);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent editEventIntent = new Intent(historyContext, EditEventActivity.class);
            habitEventController.editEvent(getPosition());
            editEventIntent.putExtra("pos",getLayoutPosition());
            ((Activity)historyContext).startActivityForResult(editEventIntent, 7);
        }


    }

    @Override
    public HabitHistoryLayoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .habit_history_row_layout, parent, false);
        return new HabitHistoryLayoutAdapter.ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(HabitHistoryLayoutAdapter.ViewHolder holder, final int position) {
        final HabitEvent habitEvent = eventList.getEvent(position);

        String comment = habitEvent.getComment();
        String location = habitEvent.getLocation();
        String type = habitEvent.getHabitType().getTitle();

        holder.eventLocation.setText(location);
        holder.eventComment.setText(comment);
        holder.eventType.setText(type);


        /*
         Button delete = holder.itemView.findViewById(R.id.deleteHabitEventButton);

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

        return habitHistoryList.size();
    }
}
