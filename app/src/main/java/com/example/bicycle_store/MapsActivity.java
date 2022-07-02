package com.example.bicycle_store;

import androidx.camera.core.Camera;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.bicycle_store.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLng hcmus = new LatLng(10.762913,106.6799776);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus,15));

        LatLng store1 = new LatLng(10.77027,106.6894009);
        LatLng store2 = new LatLng(10.767837176855316, 106.69485023977057);
        LatLng store3 = new LatLng(11.938150952308344, 108.445916415226383);
        LatLng store4 = new LatLng(10.355224949656709, 107.09614781495824);
        LatLng store5 = new LatLng(10.754559414351004, 106.657145595158);

        mMap.addMarker((new MarkerOptions().position(store1).title("Store 6")));
        mMap.addMarker((new MarkerOptions().position(store2).title("Store 2")));
        mMap.addMarker((new MarkerOptions().position(store3).title("Store 3")));
        mMap.addMarker((new MarkerOptions().position(store4).title("Store 4")));
        mMap.addMarker((new MarkerOptions().position(store5).title("Store 5")));
        mMap.addMarker((new MarkerOptions().position(hcmus).title("Main Store")));


    }
}