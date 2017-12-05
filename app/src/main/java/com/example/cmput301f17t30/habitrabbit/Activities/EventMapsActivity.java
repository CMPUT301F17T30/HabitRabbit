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

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cmput301f17t30.habitrabbit.model.Friend;
import com.example.cmput301f17t30.habitrabbit.model.HabitEvent;
import com.example.cmput301f17t30.habitrabbit.Controllers.LocationController;
import com.example.cmput301f17t30.habitrabbit.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Map<Marker, Integer> importmap = new HashMap<>();
    private LatLng current;
    private LocationController controller;
    private Marker select;
    private ArrayList<Marker> friendMarker;
    private Integer index;
    private BitmapDescriptor icon;
    private static Integer highlightflag = 0;
    private Integer listIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_maps);

        Intent intentIndex = getIntent();

        listIndex = intentIndex.getIntExtra("list",0);
        final Button highlightButton = findViewById(R.id.highlight);
        icon = BitmapDescriptorFactory.fromResource(R.drawable.star);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        controller = new LocationController(EventMapsActivity.this);

        highlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (highlightflag == 1){
                    mMap.clear();
                    highlightflag = 0;
                    drawMarker(mMap);
                } else if (highlightflag == 0){
                    //Toast.makeText(EventMapsActivity.this, "CLICKbutton!", Toast.LENGTH_SHORT).show();
                    try {
                        highLight();
                        highlightflag = 1;
                    } catch (Exception e) {

                    }
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (listIndex == 1) {
            if (MainActivity.eventController.isDelete() == 1) {
                MainActivity.eventController.resetDelete();
                importmap.clear();

                //drawMarker(mMap);
                mMap.clear();
                drawMarker(mMap);
            } else {
                try {
                    Double Lat = MainActivity.eventController.getLatitude(index);
                    Double Long = MainActivity.eventController.getLogitude(index);
                    LatLng pos = new LatLng(Lat, Long);
                    select.setPosition(pos);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));

                    double distance = CalculationByDistance(current, pos);

                    if (distance > 5) {
                        select.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                        //select = marker;
                    }
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (listIndex == 1) {
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    select = marker;
                    Intent view = new Intent(EventMapsActivity.this, ViewEventDetailActivity.class);
                    index = importmap.get(marker);
                    view.putExtra("pos", index);
                    startActivity(view);
                    return false;
                }
            });
        }

        drawMarker(mMap);

    }

    private void drawMarker(GoogleMap googleMap){
        if (listIndex == 1) {
            if (MainActivity.eventList.getSize() != 0) {
                for (Integer ind = 0; ind < MainActivity.eventList.getSize(); ind++) {
                    try {
                        Double Lat = MainActivity.eventController.getLatitude(ind);
                        Double Long = MainActivity.eventController.getLogitude(ind);
                        if (Lat != 0 && Long != 0) {
                            LatLng pos = new LatLng(Lat, Long);
                            Marker marker = googleMap.addMarker(new MarkerOptions().position(pos)
                                    .title(MainActivity.eventController.getType(ind).getTitle()));
                            importmap.put(marker, ind);
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
                        }

                    } catch (Exception e) {

                    }
                }
            }
        } else if(listIndex == 2){
            friendMarker = new ArrayList<>();
            if (MainActivity.friendsList.getSize() != 0) {
                for (Friend friend: MainActivity.friendsList.getFriends()) {
                    for (HabitEvent friendEvent: friend.getRecentEvents()) {
                        try {
                            Double Lat = friendEvent.getLatitude();
                            Double Long = friendEvent.getLogitude();
                            if (Lat != 0 && Long != 0) {
                                LatLng pos = new LatLng(Lat, Long);
                                Marker marker = googleMap.addMarker(new MarkerOptions().position(pos)
                                        .title("Friend: " + friend.getUser().getUserId())
                                        .snippet("HabbitType: " + friendEvent.getHabitType().getTitle()));
                                friendMarker.add(marker);
                                googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
                            }

                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
    }


    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));

        return Radius * c;
    }

    private void highLight(){
        int flag = controller.getGPS(EventMapsActivity.this);

        if (flag == 1){
            controller.getGpsCoordinate(EventMapsActivity.this);
            Double longitude = controller.getGpsLongitude();
            Double latitude = controller.getGpsLatitude();

            current = new LatLng(latitude,longitude);
        }
        else{
            Toast.makeText(getApplicationContext(), "Please turn on GPS.",
                    Toast.LENGTH_LONG).show();
        }

        if (listIndex == 1) {
            for (Map.Entry<Marker, Integer> entry : importmap.entrySet()) {

                Marker marker = entry.getKey();
                LatLng pos = marker.getPosition();

                double distance = CalculationByDistance(current, pos);

                if (distance <= 5) {
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.star));
                }
            }
        } else if(listIndex == 2){
            for (Marker marker: friendMarker){
                LatLng pos = marker.getPosition();
                double distance = CalculationByDistance(current, pos);
                if (distance <= 5) {
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.star));
                }
            }
        }
    }
}
