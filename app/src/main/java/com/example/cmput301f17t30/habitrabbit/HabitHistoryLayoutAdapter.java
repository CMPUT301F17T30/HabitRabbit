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
import java.util.Collections;
import java.util.Comparator;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;


/**
 * a recyclerview adapter that works with HabitHistory to dispaly the list of habit events.
 * @see com.example.cmput301f17t30.habitrabbit.HabitHistoryActivity
 */

public class HabitHistoryLayoutAdapter extends RecyclerView.Adapter<HabitHistoryLayoutAdapter.ViewHolder>{

    private ArrayList<HabitEvent> habitHistoryList;
    private Context historyContext;

    private int EVENT_DETAIL_REQUEST = 7;

    public HabitHistoryLayoutAdapter(ArrayList<HabitEvent> habitHistoryList, Context context) {
        this.habitHistoryList = new ArrayList<HabitEvent>();
        this.habitHistoryList.addAll(habitHistoryList);
        this.historyContext = context;
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView eventComment;
        private TextView eventType;
        private TextView dateText;
        private ImageView eventImage;

        public ViewHolder(View itemView) {
            super(itemView);

            eventComment = itemView.findViewById(R.id.habitEventCommentTextView);
            eventType = itemView.findViewById(R.id.eventTypeTextView);
            dateText = itemView.findViewById(R.id.dateTextView);
            eventImage = itemView.findViewById(R.id.habitEventThumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent detailEventIntent = new Intent(historyContext, ViewEventDetailActivity.class);
            eventController.editEvent(getLayoutPosition());
            detailEventIntent.putExtra("pos",getLayoutPosition());
            ((Activity)historyContext).startActivityForResult(detailEventIntent, EVENT_DETAIL_REQUEST);
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
        final HabitEvent habitEvent = habitHistoryList.get(position);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String formatDate = format.format(habitEvent.getDate());

        String comment = habitEvent.getComment();
        String type = habitEvent.getHabitType().getTitle();
        Bitmap image = habitEvent.getImage();

        holder.dateText.setText(formatDate);
        holder.eventComment.setText(comment);
        holder.eventType.setText(type);
        holder.eventImage.setImageBitmap(image);
    }


    @Override
    public int getItemCount() {
        return habitHistoryList.size();
    }
}
