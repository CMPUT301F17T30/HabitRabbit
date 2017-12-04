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

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.text.ParseException;
import java.util.EmptyStackException;
import java.util.Locale;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.achievementController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.friendsList;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.userController;

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

    private ImageController imageController = new ImageController();
    SharedPreferences sharedPreferences;

    private final Integer IMAGE_REQUEST_CODE = 0;
    private final Integer IMAGE_RESULT = 1;

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
        adapter = new AchievementLayoutAdapter(achievementController.getAchievements(), this);
        recyclerView.setAdapter(adapter);

        //initialize recyclerview for friends
        friendsRecyclerView = (RecyclerView) findViewById(R.id.friends_recycler_view);
        friendsLinearLayoutManager = new LinearLayoutManager(this);
        friendsRecyclerView.setLayoutManager(friendsLinearLayoutManager);
        friendsLayoutAdapter = new FriendsLayoutAdapter(friendsList.getFriends(), this);
        friendsRecyclerView.setAdapter(friendsLayoutAdapter);

        usernameText = (TextView) findViewById(R.id.username_text);
        usernameText.setText(userController.getUsername());

        profilePic = (ImageView) findViewById(R.id.profile_picture);

        try {
            profilePic.setImageBitmap(userController.getProfilePic());
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
                //return to main
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

        /*
        Spinner languageSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setSelection(0, false);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String language = "en";
                Locale locale;

                Configuration config = new Configuration();
                if (position == 0){
                    language = "en";
                }
                else if (position == 1){
                    language = "ru";

                }
                //Resources englishRes = getApplicationContext().getResources();
                //DisplayMetrics dm2 = englishRes.getDisplayMetrics();
                //android.content.res.Configuration conf1 = englishRes.getConfiguration();
                //conf1.locale = new Locale(language);
                //englishRes.updateConfiguration(conf1, dm2);
                locale = new Locale(language);
                Locale.setDefault(locale);
                config.locale = locale;
                getResources().updateConfiguration(config, null);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }

        });
        */
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        String username = userController.getUsername();

        menu.findItem(R.id.user_profile_button).setTitle(username);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle menu button stuff
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout_button) {
            Intent logout = new Intent(UserProfileActivity.this, LoginActivity.class);
            SharedPreferences mySPrefs =PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = mySPrefs.edit();
            editor.remove("username").apply();
            startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
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
            userController.setProfilePicture(profileImage);
            userController.saveUser();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {



        // If request is cancelled, the result arrays are empty.
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Bitmap newProfilePic = imageController.decodeFile(imageDecode);
            //profilePic.setImageBitmap(newProfilePic);
            //userController.setProfilePicture(profileImage);
            //userController.saveUser();
            // permission was granted

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
