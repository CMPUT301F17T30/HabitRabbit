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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adam on 03-Dec-17.
 */

public class RequestLayoutAdapter extends RecyclerView.Adapter<RequestLayoutAdapter.ViewHolder> {

    private ArrayList<FriendRequest> requests;
    private Context friendContext;



    public RequestLayoutAdapter(ArrayList<FriendRequest> requests, Context context) {
        this.friendContext = context;

        this.requests = requests;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView friendName;
        private ImageView friendProfile;
        private Button acceptButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            friendName = itemView.findViewById(R.id.requester_name);
            friendProfile = itemView.findViewById(R.id.requester_pic);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .request_row_layout, parent, false);
        return new ViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(RequestLayoutAdapter.ViewHolder holder, final int position) {
        final FriendRequest request = requests.get(position);

        String friendName = request.getSender();


        holder.friendName.setText(friendName);
        //holder.friendProfile.setImageBitmap(friendProfilePic);

        Button acceptButton = holder.itemView.findViewById(R.id.accept_request);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add friend to your friends list

            }
        });


    }


    @Override
    public int getItemCount() {

        return requests.size();
    }
}
