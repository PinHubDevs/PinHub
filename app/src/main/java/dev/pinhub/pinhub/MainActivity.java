package dev.pinhub.pinhub;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private TextView templateNearMe;
    private TextView templateSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.discover);
        mapFragment.getMapAsync(this);

        templateNearMe = findViewById(R.id.near_me);
        templateSearch = findViewById(R.id.search);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        HandleNavigationSwitching(bottomNavigationView);
    }

    private void HandleNavigationSwitching(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_discover:
                                mapFragment.getView().setVisibility(View.VISIBLE);
                                templateNearMe.setVisibility(View.GONE);
                                templateSearch.setVisibility(View.GONE);
                                break;
                            case R.id.action_near_me:
                                mapFragment.getView().setVisibility(View.GONE);
                                templateNearMe.setVisibility(View.VISIBLE);
                                templateSearch.setVisibility(View.GONE);
                                break;
                            case R.id.action_search:
                                mapFragment.getView().setVisibility(View.GONE);
                                templateNearMe.setVisibility(View.GONE);
                                templateSearch.setVisibility(View.VISIBLE);
                                break;
                        }
                        return false;
                    }
                });
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
        LatLng vilnius = new LatLng(54.674886, 25.273520);
        mMap.addMarker(new MarkerOptions().position(vilnius).title("Marker in Vilnius"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vilnius));
    }
}
