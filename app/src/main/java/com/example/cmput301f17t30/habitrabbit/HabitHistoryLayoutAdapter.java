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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity.habitEventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;
import static java.lang.Boolean.FALSE;

/**
 * a recyclerview adapter that works with HabitHistory to dispaly the list of habit events.
 * @see com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity
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
        private TextView dateText;
        private ImageView eventImage;


        public ViewHolder(View itemView) {
            super(itemView);

            //eventLocation = itemView.findViewById(R.id.habitEventLocationTextView);
            eventComment = itemView.findViewById(R.id.habitEventCommentTextView);
            eventType = itemView.findViewById(R.id.eventTypeTextView);
            dateText = itemView.findViewById(R.id.dateTextView);
            eventImage = itemView.findViewById(R.id.habitEventThumbnail);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent detailEventIntent = new Intent(historyContext, ViewEventDetailActivity.class);
            habitEventController.editEvent(getPosition());
            detailEventIntent.putExtra("pos",getLayoutPosition());
            ((Activity)historyContext).startActivityForResult(detailEventIntent, 7);
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

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String formatDate = format.format(habitEvent.getDate());


        String comment = habitEvent.getComment();
        //String location = habitEvent.getLocation();
        String type = habitEvent.getHabitType().getTitle();
        Bitmap image = habitEvent.getImage();

        //holder.eventLocation.setText(location);
        holder.dateText.setText(formatDate);
        holder.eventComment.setText(comment);
        holder.eventType.setText(type);
        holder.eventImage.setImageBitmap(image);


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
