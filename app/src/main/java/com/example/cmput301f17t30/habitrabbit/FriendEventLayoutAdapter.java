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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cmput301f17t30.habitrabbit.MockClasses.MockHabit;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Adapter for displaying the latest events complete by a users friend
 * @see com.example.cmput301f17t30.habitrabbit.FriendActivity
 */

public class FriendEventLayoutAdapter extends RecyclerView.Adapter<FriendEventLayoutAdapter.ViewHolder> {

    private ArrayList<HabitEvent> friendEvents = new ArrayList<>();
    private Context friendContext;

    public FriendEventLayoutAdapter(ArrayList<HabitEvent> friendList, Context context) {
        this.friendContext = context;

        this.friendEvents = friendList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView friendName;
        private ImageView friendImage;
        private TextView friendDate;
        private TextView friendType;
        private TextView friendComment;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            friendName = itemView.findViewById(R.id.friend_event_username);
            friendImage = itemView.findViewById(R.id.friend_event_image);
            friendDate = itemView.findViewById(R.id.friend_event_date);
            friendType = itemView.findViewById(R.id.friend_event_type);
            friendComment = itemView.findViewById(R.id.friend_event_comment);
        }

        @Override
        public void onClick(View view) {
            //go to friends profile page
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .friend_event_row_layout, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(FriendEventLayoutAdapter.ViewHolder holder, final int position) {
        final HabitEvent event = friendEvents.get(position);
        String username = event.getUserId();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(event.getDate());
        String type = event.getHabitType().getTitle();
        String comment = event.getComment();

        holder.friendName.setText(username);
        if (event.getImage() != null) {
            holder.friendImage.setImageBitmap(event.getImage());
        }
        holder.friendDate.setText(date);
        holder.friendType.setText(type);
        holder.friendComment.setText(comment);

    }


    @Override
    public int getItemCount() {

        return friendEvents.size();
    }
}