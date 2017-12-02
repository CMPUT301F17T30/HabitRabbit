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
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.friendController;

/**
 * Adapter for displaying the latest events complete by a users friend
 * @See FriendActivity
 */

public class FriendLayoutAdapter extends RecyclerView.Adapter<FriendLayoutAdapter.ViewHolder> {

    private ArrayList<Friend> friends;
    private Context friendContext;



    public FriendLayoutAdapter(ArrayList<Friend> friendList, Context context) {
        this.friendContext = context;

        friends = friendController.getFriends();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView friendName;
        private ImageView friendImage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            friendName = itemView.findViewById(R.id.friend_event_comment);
            friendImage = itemView.findViewById(R.id.friend_event_image);
        }

        @Override
        public void onClick(View view) {
            //go to friends profile page
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .friend_row_layout, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(FriendLayoutAdapter.ViewHolder holder, final int position) {
        final User friend = friends.get(position);

        //holder.friendName.setText(name);
        //holder.friendImage.setImageBitmap(friendProfilePic);


    }


    @Override
    public int getItemCount() {

        return friends.size();
    }
}