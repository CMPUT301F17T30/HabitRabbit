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

import java.util.ArrayList;

/**
 * Adapter for displaying list of friends
 * @see com.example.cmput301f17t30.habitrabbit.UserProfileActivity
 */

public class FriendsLayoutAdapter extends RecyclerView.Adapter<FriendsLayoutAdapter.ViewHolder> {

    private ArrayList<Friend> friendsList;
    private Context profileContext;

    public FriendsLayoutAdapter(ArrayList<Friend> friendsList, Context context) {
        this.profileContext = context;
        this.friendsList = friendsList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView friendUsername;
        private ImageView friendPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            friendUsername = itemView.findViewById(R.id.friend_username);
            friendPicture = itemView.findViewById(R.id.friend_profile_pic);

        }

        @Override
        public void onClick(View view) {
            // go to friend profile
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .friends_row_layout, parent, false);
        return new ViewHolder(inflatedView);
    }
    @Override
    public void onBindViewHolder(FriendsLayoutAdapter.ViewHolder holder, final int position) {
        final Friend friend = friendsList.get(position);
        String name = friend.getUser().getUserId();

        holder.friendUsername.setText(name);

        if (friend.getUser().getProfilePic() != null){
            holder.friendPicture.setImageBitmap(friend.getUser().getProfilePic());
        }
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}