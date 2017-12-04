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

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.friendRequests;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

/**
 * Created by Adam on 03-Dec-17.
 */

public class AddFriendDialogue extends Dialog {

    private FriendActivity activity;
    private AddFriendDialogue thisDialog;

    private EditText sendFriendText;

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RequestLayoutAdapter adapter;
    private ArrayList<FriendRequest> adapterList;

    public AddFriendDialogue(FriendActivity context){
        super(context);
        this.activity = context;
        this.thisDialog = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friends_dialogue);
        getWindow().setLayout(android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        initalize();
    }

    private void initalize() {
        sendFriendText = findViewById(R.id.search_friend_username);


        adapterList = friendRequests.getFriendRequests();


        recyclerView = findViewById(R.id.requests_recyclerview);
        linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new RequestLayoutAdapter(adapterList, activity);
        recyclerView.setAdapter(adapter);

        Button addFriendButton = findViewById(R.id.send_request_button);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetWorkCheck.isOnline()) {
                    //generate friend request
                    String newFriend = sendFriendText.getText().toString();
                    FriendRequest request = new FriendRequest(userController.getUsername(), newFriend);

                    //upload request to elastic
                    ElasticSearchController.AddFriendRequestTask addRequest = new ElasticSearchController.AddFriendRequestTask();
                    addRequest.execute(request);

                    //clear textbox and notify user of success
                    sendFriendText.setText("");
                    Toast.makeText(activity, "Friend added", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(activity, "You are not connected to the internet", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
