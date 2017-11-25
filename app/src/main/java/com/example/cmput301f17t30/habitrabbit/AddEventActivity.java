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
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.habitList;


/**
 * The activity for adding a new habit event.
 *
 */
public class AddEventActivity extends AppCompatActivity {
    private EditText comment, locationInput;
    private Intent intent;

    // use intent to pass habit for actual code
    private Habit habit;

    //indicator
    private static int IMG_RESULT = 1;
    private static final int IMAGE_REQUEST_CODE = 2;
    private static final int GPS_REQUEST_CODE = 3;

    //image
    private ImageView image;
    private String ImageDecode;
    private Bitmap selectImage;
    private ImageController imageController = new ImageController();


    private View layout;

    //location
    private Button searchButton;
    private ListView locationOuput;

    private ArrayAdapter<String> adapter;

    private ArrayList<String> locationNameList;

    //location
    private double latitude;
    private double longitude;
    private String addressName;
    private Bitmap defaultImage;


    private LocationController locationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        // pass activity to location controller
        locationController = new LocationController(this);


        final Button addImage = (Button) findViewById(R.id.add_image);
        final Button saveButton = (Button) findViewById(R.id.save_event);
        final Button gpsButton = (Button) findViewById(R.id.gps);
        image = (ImageView) findViewById(R.id.ivImage);
        comment = (EditText) findViewById(R.id.comment);



        locationInput = (EditText) findViewById(R.id.enter_location);
        searchButton = (Button) findViewById(R.id.search_location);
        locationOuput = (ListView) findViewById(R.id.serchout);


        locationNameList = new ArrayList<>(); //empty in start
        //
        adapter = new ArrayAdapter<>(this, R.layout.list_location, locationNameList);
        locationOuput.setAdapter(adapter);


        // add image
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMG_RESULT);


            }
        });

        /*
          select location from search result
         */
        locationOuput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parentView, View childView,
                                    int position, long id) {

                addressName = locationController.setLocationName(position);
                longitude = locationController.getLongitude();
                latitude = locationController.getLatitude();
                eventController.setCoordinate(longitude, latitude);
                eventController.setLocationName(addressName);

                locationInput.setText(addressName);
                // clear the search history
                locationNameList.clear();
                adapter.notifyDataSetChanged();


            }

        });

        /*
          reponse to the search button
          search location
         */
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationNameList.clear();
                String locationName = locationInput.getText().toString();
                locationNameList.addAll(locationController.getLocationList(v.getContext(), locationName));
                adapter.notifyDataSetChanged();
            }
        });

        /*
           response to gps button clicked
           call getGPSlocation() to get gps service
         */
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = locationController.getGPS(v.getContext());
                if (flag == 1) {
                    checkPermission(GPS_REQUEST_CODE);
                    addressName = locationController.getGpsAddressName(v.getContext());
                    eventController.setLocationName(addressName);
                    longitude = locationController.getGpsLongitude();
                    latitude = locationController.getGpsLatitude();
                    eventController.setCoordinate(longitude, latitude);
                    locationInput.setText(addressName);
                } else {
                    Toast.makeText(getApplicationContext(), "please turn on GPS", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
          set up the habit event object and save
         */
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String theComment = comment.getText().toString();

            if (theComment.length() > 20) {
                Toast.makeText(getApplicationContext(), "Please keep comment under 20 characters.",
                        Toast.LENGTH_LONG).show();
            }

            else
                eventController.setComment(theComment);
                eventController.saveAddEvent();
                //addEventDone();

                Intent returnToMain = new Intent();
                setResult(RESULT_OK, returnToMain);

                finish();


            }
        });
    }

    public void addEventDone(){
        try{


            Intent returnToMain = new Intent();
            setResult(RESULT_OK, returnToMain);

            finish();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * call this to open photo gallery outside the app
     * then once the user select the image, get the path of the image
     * to use the path, call permission to access external storage
     *
     * @param requestCode The indicator for select image operation
     * @param resultCode Whether the activity returned properly
     * @param data the context of AddEventActivity activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == IMG_RESULT && resultCode == RESULT_OK && null != data) {
                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(URI, FILE, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();
                // check permission to get image
                checkPermission(IMAGE_REQUEST_CODE);

            }
        } catch (Exception e) {
            // unable to get the path
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }

    }



    /**
     * Ask user for permission at runtime
     * one is when user need to get the image then
     * check for read external storage permission
     * one is when user need to get the current location using gps
     * check for access location permission
     *
     * @param requestType the indicator for gps or image permission
     */
    private void checkPermission(int requestType) {

        switch (requestType) {
            // access to gps service
            case GPS_REQUEST_CODE: {
                final String permission = Manifest.permission.ACCESS_FINE_LOCATION;
                // if no permission, ask for permission
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                        ActivityCompat.requestPermissions(AddEventActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);

                    } else {
                        ActivityCompat.requestPermissions(AddEventActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);

                    }
                } else {
                    // has permission, get gps
                    locationController.getGpsCoordinate(AddEventActivity.this);

                }
                return;
            }

            // access to external storage to get image
            case IMAGE_REQUEST_CODE: {
                final String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                // no permission, ask permission
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                        ActivityCompat.requestPermissions(AddEventActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

                    } else {
                        ActivityCompat.requestPermissions(AddEventActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

                    }
                } else {
                    // has permission, get the image
                    selectImage = imageController.decodeFile(ImageDecode);
                    image.setImageBitmap(selectImage);
                    eventController.setImage(selectImage);
                }
                return;


            }

        }
    }

    /**
     * call from checkPermission if no permission is granted
     * then ask the user to give permissions
     *
     * @param requestCode the indicator for gps or image permission
     * @param permissions the sentence of permission request
     * @param grantResults the result of permission request
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        switch (requestCode) {
            case IMAGE_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectImage = imageController.decodeFile(ImageDecode);
                    image.setImageBitmap(selectImage);
                    eventController.setImage(selectImage);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    Toast.makeText(AddEventActivity.this, "Permission needed to access photo gallery.", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            case GPS_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermission(GPS_REQUEST_CODE);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    Toast.makeText(AddEventActivity.this, "Permission needed to access GPS services.", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        eventController.addEvent(habit);
    }

}




