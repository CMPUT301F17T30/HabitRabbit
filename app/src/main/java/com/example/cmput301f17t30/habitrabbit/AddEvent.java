package com.example.cmput301f17t30.habitrabbit;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
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
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddEvent extends AppCompatActivity {
    private EditText comment;
    Intent intent;
    private static int IMG_RESULT = 1;

    private static final int IMAGE_REQUEST_CODE = 2;
    private static final int GPS_REQUEST_CODE = 3;

    private ImageView image;
    private String ImageDecode;

    private Bitmap selectImage;


    private View layout;
    //location
    EditText searchIn;
    Button searchButton;
    ListView searchOut;

    private ArrayAdapter<String> adapter;

    Geocoder geocoder;
    final static int maxResults = 5;
    List<Address> locationList;
    List<String> locationNameList;
    //endl

    int addressIndex;
    double latitude;
    double longitude;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location location;

    boolean isGPSenable;
    float minDistanceChanged = 10;
    long minTime = 1000 * 60 * 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        final Button addImage = (Button) findViewById(R.id.add_image);
        //final Button addLocation = (Button) findViewById(R.id.add_location);
        final Button saveButton = (Button) findViewById(R.id.save_event);

        final Button gpsButton = (Button) findViewById(R.id.gps);

        image = (ImageView) findViewById(R.id.ivImage);

        comment = (EditText) findViewById(R.id.command);


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMG_RESULT);
                //image.setImageBitmap(selectImage);


            }
        });


        searchIn = (EditText) findViewById(R.id.enter_location);
        searchButton = (Button) findViewById(R.id.search_location);
        searchOut = (ListView) findViewById(R.id.serchout);

        //searchButton.setOnClickListener(searchButtonOnClickListener);

        geocoder = new Geocoder(this, Locale.ENGLISH);

        locationNameList = new ArrayList<String>(); //empty in start
        adapter = new ArrayAdapter<String>(this, R.layout.list_location, locationNameList);
        searchOut.setAdapter(adapter);

        searchOut.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parentView, View childView,
                                    int position, long id) {
                addressIndex = position;

                longitude = locationList.get(addressIndex).getLongitude();
                latitude = locationList.get(addressIndex).getLatitude();

                searchIn.setText(locationList.get(addressIndex).getFeatureName() + ", " + locationList.get(addressIndex).getLocality());

                locationNameList.clear();
                adapter.notifyDataSetChanged();


            }

            public void onNothingSelected(AdapterView parentView) {

            }
        });
        //location
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String locationName = searchIn.getText().toString();


                //Toast.makeText(getApplicationContext(), "Search for: " + locationName, Toast.LENGTH_SHORT).show();

                if (locationName == null) {
                    Toast.makeText(getApplicationContext(), "locationName == null", Toast.LENGTH_LONG).show();
                } else {
                    try {

                        locationList = geocoder.getFromLocationName(locationName, maxResults);

                        if (locationList == null) {
                            Toast.makeText(getApplicationContext(),
                                    "locationList == null",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            if (locationList.isEmpty()) {
                                Toast.makeText(getApplicationContext(),
                                        "locationList is empty",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                //Toast.makeText(getApplicationContext(),"number of result: " + locationList.size(),Toast.LENGTH_LONG).show();

                                locationNameList.clear();

                                for (Address i : locationList) {
                                    if (i.getFeatureName() == null) {
                                        locationNameList.add("unknown");
                                    } else {
                                        //StringBuilder sb = new StringBuilder();
                                        //sb.append(i.getAddressLine(0) + ", " + i.getAddressLine(1) + "\n" + i.getAddressLine(2));
                                        //locationNameList.add(i.getAddressLine(0).toString());
                                        //locationNameList.add(i.getLocality());
                                        //locationNameList.add(sb.toString());
                                        String addressline = i.getFeatureName() + '\n' + i.getAddressLine(0) + i.getAddressLine(1) + ", " + i.getAddressLine(2);
                                        locationNameList.add(addressline);
                                    }
                                }

                                adapter.notifyDataSetChanged();
                            }
                        }


                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),
                                "network unavailable or any other I/O problem occurs" + locationName,
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }

            }
        });

        //gps button
        gpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getGPSlocation();
                Log.d("coordinate: ", longitude + ", " + latitude);

            }
        });
    }

    private void getGPSlocation() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        isGPSenable = locationManager.isProviderEnabled("gps");
        //boolean isLocationAble =
        Log.d("isGPS", ""+isGPSenable);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("kkkkkkkkkkkk", "d");
                if (location != null){
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    Log.d("current:", ""+ longitude + "," + latitude);
                }
                else{
                    Toast.makeText(getApplicationContext(), "cannot get current locatoin",Toast.LENGTH_LONG).show();
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
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        if (isGPSenable) {
            Log.d("herehere", "d");
            checkPermission(GPS_REQUEST_CODE);
        }else {
            Toast.makeText(getApplicationContext(), "please turn on GPS",Toast.LENGTH_LONG).show();
        }
        //checkPermission(GPS_REQUEST_CODE);


        //locationManager.requestLocationUpdates("gps", minTime, minDistanceChanged, locationListener);

        /*locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        isGPSenable = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);
        isNetworkEnable = locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);

        if (isNetworkEnable) {
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, minTime, minDistanceChanged, this);
        }

        if*/

    }



    private void checkPermission(int requestType) {

        switch (requestType) {
            case GPS_REQUEST_CODE: {
                final String permission = Manifest.permission.ACCESS_FINE_LOCATION;
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    Log.d("check permission", "kkkk");
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                        ActivityCompat.requestPermissions(AddEvent.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);

                    } else {
                        ActivityCompat.requestPermissions(AddEvent.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE);

                    }
                } else {
                    locationManager.requestLocationUpdates("gps", minTime, minDistanceChanged, locationListener);
                    Log.d("location assign", "kkkkkkkkkkk");
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    locationListener.onLocationChanged(location);

                }
                return;
            }

            case IMAGE_REQUEST_CODE: {
                final String permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                        ActivityCompat.requestPermissions(AddEvent.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

                    } else {
                        ActivityCompat.requestPermissions(AddEvent.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, IMAGE_REQUEST_CODE);

                    }
                } else {
                    decodeFile(ImageDecode);
                }
                return;


            }

        }
    }
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
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("no permission", "kkkkk");
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

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                    && null != data) {
                Uri URI = data.getData();
                String[] FILE = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(URI,
                        FILE, null, null, null);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(FILE[0]);
                ImageDecode = cursor.getString(columnIndex);
                cursor.close();

                checkPermission(IMAGE_REQUEST_CODE);

                //image.setImageBitmap(BitmapFactory.decodeFile(ImageDecode));

            }
        } catch (Exception e) {
            Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                    .show();
        }


    }

    public void decodeFile(String filePath) {
        //set max image file size
        int maxSize = 65536;

        int currentSize;
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        // The new size we want to scale to 500
        final int REQUIRED_SIZE = 500;
        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        Log.d("sb0: ", ""+scale);
        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        selectImage = BitmapFactory.decodeFile(filePath, o2);
        //currentSize = selectImage.getByteCount();
        int compressQuality = 100;
        int streamLength = maxSize;
        Log.d("sb1: ", "Size: " + streamLength);

        while (streamLength >= maxSize) {
            compressQuality -= 1;
            Log.d("quality: ", "Size: " + compressQuality);
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            selectImage.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream);

            byte[] bmpPicByteArray = bmpStream.toByteArray();
            streamLength = bmpPicByteArray.length;
            Log.d("sb2: ", "Size: " + streamLength);
        }

        image.setImageBitmap(selectImage);

    }
}




