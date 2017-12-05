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

package com.example.cmput301f17t30.habitrabbit.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.example.cmput301f17t30.habitrabbit.model.Friend;
import com.example.cmput301f17t30.habitrabbit.Controllers.ImageController;
import com.example.cmput301f17t30.habitrabbit.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserProfileActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AchievementLayoutAdapter adapter;

    private RecyclerView friendsRecyclerView;
    private LinearLayoutManager friendsLinearLayoutManager;
    private FriendsLayoutAdapter friendsLayoutAdapter;

    private Bitmap profileImage;
    private String imageDecode;

    private TextView usernameText;
    private ImageView profilePic;
    private TextView joinDateText;

    private ImageController imageController = new ImageController();
    SharedPreferences sharedPreferences;

    private final Integer IMAGE_REQUEST_CODE = 0;
    private final Integer IMAGE_RESULT = 1;

    private ArrayList<Friend> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final ViewFlipper viewFlipper = (ViewFlipper) findViewById(R.id.profile_viewflipper);

        //initialize recyclerview for achievements
        recyclerView = (RecyclerView) findViewById(R.id.achievementsLayout);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AchievementLayoutAdapter(MainActivity.achievementController.getAchievements(), this);
        recyclerView.setAdapter(adapter);

        //initialize recyclerview for friends
        friends = MainActivity.friendsList.getFriends();
        friendsRecyclerView = (RecyclerView) findViewById(R.id.friends_recycler_view);
        friendsLinearLayoutManager = new LinearLayoutManager(this);
        friendsRecyclerView.setLayoutManager(friendsLinearLayoutManager);
        friendsLayoutAdapter = new FriendsLayoutAdapter(friends, this);
        friendsRecyclerView.setAdapter(friendsLayoutAdapter);

        usernameText = (TextView) findViewById(R.id.username_text);
        usernameText.setText(MainActivity.userController.getUsername());
        joinDateText = (TextView) findViewById(R.id.user_join_date);

        if (MainActivity.userController.getJoinDate() != null) {
            //set the join date
            String pattern = "dd-MM-yyyy";
            String stringDate = new SimpleDateFormat(pattern).format(MainActivity.userController.getJoinDate());
            joinDateText.setText("Joined on " + stringDate);
        }
        else{
            //user is new, use today's date
            String pattern = "dd-MM-yyyy";
            String stringDate = new SimpleDateFormat(pattern).format(MainActivity.userController.getJoinDate());
            joinDateText.setText("Joined on " + stringDate);
        }

        profilePic = (ImageView) findViewById(R.id.profile_picture);

        try {
            profilePic.setImageBitmap(MainActivity.userController.getProfilePic());
        }
        catch (NullPointerException exception){
            //do not set picture if it doesnt exist
            Log.d("error", "user has no profile pic or user is null" );
        }


        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE_RESULT);
            }
        });

        Button achievementsButton = (Button) findViewById(R.id.achievements_button);
        achievementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.achievementsLayout)));
            }
        });

        Button settingsButton = (Button) findViewById(R.id.return_to_main);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    MainActivity.achievementController.saveAchievementsStatus();
                    MainActivity.userController.saveUser();
                }
                catch (NullPointerException e){
                    Log.d("error","unable to save achievements");
                }
                finish();
            }
        });

        Button friendsButton = (Button) findViewById(R.id.friends_button);
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper.setDisplayedChild(viewFlipper.indexOfChild(findViewById(R.id.friends_recycler_view)));
            }
        });

    }

    private void checkPermission(int requestType) {

        final String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
        // no permission, ask permission
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                ActivityCompat.requestPermissions(UserProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

            } else {
                ActivityCompat.requestPermissions(UserProfileActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

            }
        } else {
            // has permission, get the image
            profileImage = imageController.decodeFile(imageDecode);
            profilePic.setImageBitmap(profileImage);
            MainActivity.userController.setProfilePicture(profileImage);
            MainActivity.userController.saveUser();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {



        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            Toast.makeText(UserProfileActivity.this, "Permission needed to access photo gallery.", Toast.LENGTH_SHORT).show();
            // permission denied
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == IMAGE_RESULT && resultCode == RESULT_OK && null != data) {
                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(URI, FILE, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(FILE[0]);
                imageDecode = cursor.getString(columnIndex);
                cursor.close();
                // check permission to get image
                checkPermission(IMAGE_REQUEST_CODE);

            }
        } catch (NullPointerException e) {
            // unable to get the path
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
