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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.cmput301f17t30.habitrabbit.MockClasses.MockHabit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.friendController;

public class FriendActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FriendEventLayoutAdapter adapter;

    private ArrayList<Friend> friends;
    private ArrayList<HabitEvent> recentEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        recentEvents = new ArrayList<>();
        recentEvents.clear();
        friends = friendController.getFriends();
        for (Friend friend : friends){
            ArrayList<HabitEvent> recent = new ArrayList<>();
            recent.addAll(friend.getRecentEvents());
            recentEvents.addAll(recent);
        }
        Collections.sort(recentEvents,new FriendEventSorter());


        recyclerView = (RecyclerView) findViewById(R.id.friends_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new FriendEventLayoutAdapter(recentEvents, this);
        recyclerView.setAdapter(adapter);


        Button returnToMain = (Button) findViewById(R.id.main_button);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Button filterButton = (Button) findViewById(R.id.add_accept_friend);
        filterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddFriendDialogue addDialog = new AddFriendDialogue(FriendActivity.this);
                addDialog.show();
            }
        });

        Button mapButton = (Button) findViewById(R.id.friends_map);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(FriendActivity.this, EventMapsActivity.class);
                map.putExtra("list", 2);
                startActivity(map);
            }
        });
    }

    public class FriendEventSorter implements Comparator<HabitEvent> {
        public int compare(HabitEvent left, HabitEvent right) {
            if (left.getUserId().compareToIgnoreCase(right.getUserId()) == -1) {
                return -1;
            }
            else if (right.getUserId().compareToIgnoreCase(left.getUserId()) == 1) {
                return 1;
            }

            else if (right.getUserId().compareToIgnoreCase(left.getUserId()) == 0)  {
                if (left.getHabitType().getTitle().compareToIgnoreCase(right.getHabitType().getTitle()) == -1) {
                    return -1;
                } else if (right.getHabitType().getTitle().compareToIgnoreCase(left.getHabitType().getTitle()) == 1) {
                    return 1;
                }
            }
                return 0;
        }
    }
}
