package com.example.cmput301f17t30.habitrabbit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 *  the activity to create new habit event object and save
 *
 *  @version : 1
 *
 */
public class AddEvent extends AppCompatActivity {
    private EditText comment, locationInput;
    private Intent intent;


    // mockup habit event here
    // use intent to pass habit for actual code
    ArrayList daylist = new ArrayList<Boolean>();
    Habit habit = new Habit("title 1", "test", daylist);
    private HabitEvent newHabitEvent = new HabitEvent(habit);

    //indicator
    private static int IMG_RESULT = 1;
    private static final int IMAGE_REQUEST_CODE = 2;
    private static final int GPS_REQUEST_CODE = 3;

    //image
    private ImageView image;
    private String ImageDecode;
    private Bitmap selectImage;


    private View layout;

    //location
    private Button searchButton;
    private ListView locationOuput;

    private ArrayAdapter<String> adapter;

    private Geocoder geocoder;
    final static int maxResults = 2;
    private List<Address> locationList;
    private List<String> locationNameList;




    //location
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location;
    private Address a;
    private int addressIndex;
    private double latitude;
    private double longitude;
    private String addressName;

    //GPS
    boolean isGPSenable;
    float minDistanceChanged = 5;
    long minTime = 1000 * 60 * 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        final Button addImage = (Button) findViewById(R.id.add_image);
        final Button saveButton = (Button) findViewById(R.id.save_event);
        final Button gpsButton = (Button) findViewById(R.id.gps);
        image = (ImageView) findViewById(R.id.ivImage);
        comment = (EditText) findViewById(R.id.comment);




        locationInput = (EditText) findViewById(R.id.enter_location);
        searchButton = (Button) findViewById(R.id.search_location);
        locationOuput = (ListView) findViewById(R.id.serchout);


        //searchButton.setOnClickListener(searchButtonOnClickListener);

        geocoder = new Geocoder(this, Locale.ENGLISH);

        locationNameList = new ArrayList<String>(); //empty in start
        adapter = new ArrayAdapter<String>(this, R.layout.list_location, locationNameList);
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

        /**
         * select location from search result
         */
        locationOuput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parentView, View childView,
                                    int position, long id) {
                addressIndex = position;
                a = locationList.get(addressIndex);
                longitude = locationList.get(addressIndex).getLongitude();
                latitude = locationList.get(addressIndex).getLatitude();
                addressName = a.getAddressLine(0) + a.getAddressLine(1) + a.getAddressLine(2);
                locationInput.setText(a.getFeatureName() + ", " + a.getLocality());

                // set longitude and latitude and location name to new habit event
                newHabitEvent.setCoordinate(longitude, latitude);
                newHabitEvent.setLocation(addressName);

                // clear the search history
                locationNameList.clear();
                adapter.notifyDataSetChanged();


            }

        });

        /**
         * reponse to the search button
         * search location
         */
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                String locationName = locationInput.getText().toString();

                // user did not input anything
                if (locationName == null) {
                    Toast.makeText(getApplicationContext(), "Please enter location.", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        // user inputed location name
                        // use geocoder to search
                        locationList = geocoder.getFromLocationName(locationName, maxResults);

                        if (locationList == null) {
                            Toast.makeText(getApplicationContext(), "Cannot get location", Toast.LENGTH_LONG).show();
                        } else {
                            if (locationList.isEmpty()) {
                                Toast.makeText(getApplicationContext(), "No location is found", Toast.LENGTH_LONG).show();
                            } else {
                                locationNameList.clear();
                                for (Address i : locationList) {
                                    if (i.getFeatureName() == null) {
                                        locationNameList.add("Unknown location");
                                    } else {
                                        String addressline = i.getFeatureName() + '\n'
                                                + i.getAddressLine(0) + i.getAddressLine(1) + ", " + i.getAddressLine(2);
                                        locationNameList.add(addressline);
                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }


                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "Network unavailable or any issues occurred.",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }

            }
        });

        /**
         *  response to gps button clicked
         *  call getGPSlocation() to get gps service
         */
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGPSlocation();
            }
        });

        /**
         * set up the habit event object and save
         */
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String theComment = comment.getText().toString();
                if (theComment.length() > 0) {
                    if (theComment.length() < 20) {
                        newHabitEvent.setComment(theComment);
                        newHabitEvent.getHabitType().incrementTimesCompleted();
                        // save in file function here or habit event will gone

                    } else {
                        Toast.makeText(getApplicationContext(), "Please keep comment under 20 characters.",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    newHabitEvent.setComment("");
                    newHabitEvent.getHabitType().incrementTimesCompleted();
                    // save in file function here or habit event will gone
                }

            }
        });
    }

    /**
     * get gps service and check permission
     * if location is gotten, get the name of the location
     */
    private void getGPSlocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        isGPSenable = locationManager.isProviderEnabled("gps");
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null){
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    // set longitude and latitude for new habit event
                    newHabitEvent.setCoordinate(longitude, latitude);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Cannot get current locatoin.",Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        // check if the gps provider is available
        if (isGPSenable) {
            // call to check permission to access gps
            checkPermission(GPS_REQUEST_CODE);
            // try to get location name
            try {
                List<Address> result = geocoder.getFromLocation(latitude, longitude, maxResults);
                if (result == null) {
                    Toast.makeText(getApplicationContext(), "Cannot get location name.",
                            Toast.LENGTH_LONG).show();
                } else {
                    if (result.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "No location is found.",
                                Toast.LENGTH_LONG).show();
                    } else {
                        a = result.get(0);
                        addressName = a.getAddressLine(0) + a.getAddressLine(1) + a.getAddressLine(2);
                        locationInput.setText(a.getFeatureName() + ", " + a.getLocality());
                        // set location name in habit event
                        newHabitEvent.setLocation(addressName);
                    }
                }
            }catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Network unavailable to get location name.",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }else {
            Toast.makeText(getApplicationContext(), "please turn on GPS",Toast.LENGTH_LONG).show();
        }

    }

    /**
     * call this to open photo gallery outside the app
     * then once the user select the image, get the path of the image
     * to use the path, call permission to access external storage
     *
     * @param requestCode The indicator for select image operation
     * @param resultCode
     * @param data the context of AddEvent activity
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
     * pass the file path to decode it into bitmap
     * then resize and compress it to desired file size
     * then set it to image view to show it
     *
     * @param filePath the file path of image in this phone
     */
    public void decodeFile(String filePath) {
        //set max image file size
        int maxSize = 65536;

        // get the original image size
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, option);

        // The new size we want to scale to 500
        final int REQUIRED_SIZE = 500;
        // Find the correct scale value. It should be the power of 2.
        int width_origin = option.outWidth, height_origin = option.outHeight;
        int scale = 1;
        while (true) {
            if (width_origin < REQUIRED_SIZE && height_origin < REQUIRED_SIZE)
                break;
            width_origin /= 2;
            height_origin /= 2;
            scale *= 2;
        }

        // get image with desired size
        BitmapFactory.Options optionSet = new BitmapFactory.Options();
        optionSet.inSampleSize = scale;
        selectImage = BitmapFactory.decodeFile(filePath, optionSet);

        // compress the image to desired file size
        int compressQuality = 100;
        int streamLength = maxSize;

        while (streamLength >= maxSize) {
            compressQuality -= 1;
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            selectImage.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream);

            byte[] bmpPicByteArray = bmpStream.toByteArray();
            streamLength = bmpPicByteArray.length;
        }

        // set the image to image view
        image.setImageBitmap(selectImage);
        // set image to new habit event
        newHabitEvent.setImage(selectImage);

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
                        ActivityCompat.requestPermissions(AddEvent.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);

                    } else {
                        ActivityCompat.requestPermissions(AddEvent.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);

                    }
                } else {
                    // has permission, get gps
                    locationManager.requestLocationUpdates("gps", minTime, minDistanceChanged, locationListener);
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    locationListener.onLocationChanged(location);

                }
                return;
            }

            // access to external storage to get image
            case IMAGE_REQUEST_CODE: {
                final String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                // no permission, ask permission
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                        ActivityCompat.requestPermissions(AddEvent.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

                    } else {
                        ActivityCompat.requestPermissions(AddEvent.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

                    }
                } else {
                    // has permission, get the image
                    decodeFile(ImageDecode);
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
                    decodeFile(ImageDecode);
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    Toast.makeText(AddEvent.this, "Permission needed to access photo gallery.", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AddEvent.this, "Permission needed to access GPS services.", Toast.LENGTH_SHORT).show();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }
    }


}




