package com.example.cmput301f17t30.habitrabbit;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventController;
import static com.example.cmput301f17t30.habitrabbit.MainActivity.eventList;

public class EventMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Map<Marker, Integer> importmap = new HashMap<Marker, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    protected void onResume(){
        super.onResume();
        drawMarker(mMap);

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
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent view = new Intent(EventMapsActivity.this, ViewEventDetailActivity.class);
                Integer index = importmap.get(marker);
                view.putExtra("pos", index);
                startActivity(view);

                return false;
            }
        });

        drawMarker(mMap);

    }

    private void drawMarker(GoogleMap googleMap){
        if (eventList.getSize() != 0){
            for (Integer ind = 0; ind < eventList.getSize(); ind++) {
                try {
                    Double Lat = eventController.getLatitude(ind);
                    Double Long = eventController.getLogitude(ind);
                    LatLng pos = new LatLng(Lat, Long);
                    Marker marker = googleMap.addMarker(new MarkerOptions().position(pos)
                            .title(eventController.getType(ind).getTitle()));
                    importmap.put(marker, ind);
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
                } catch (Exception e){

                }
            }
        }
    }
}
