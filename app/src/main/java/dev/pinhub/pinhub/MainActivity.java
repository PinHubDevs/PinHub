package dev.pinhub.pinhub;

import android.arch.lifecycle.ViewModelProviders;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import dev.pinhub.pinhub.LocationUtilities.LocationCallback;
import dev.pinhub.pinhub.LocationUtilities.LocationUtil;
import dev.pinhub.pinhub.fragments.DiscountedItemFragment;
import dev.pinhub.pinhub.fragments.SearchViewFragment;
import dev.pinhub.pinhub.fragments.ShopCardListFragment;
import dev.pinhub.pinhub.models.DiscountedItem;
import dev.pinhub.pinhub.models.MainActivityViewModel;
import dev.pinhub.pinhub.models.ShopCard;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback,
        DiscountedItemFragment.OnListFragmentInteractionListener,
        ShopCardListFragment.OnListFragmentInteractionListener{

    private static final String MAP_FRAGMENT_NAME = "map_fragment";
    private static final String NEAR_ME_FRAGMENT_NAME = "near_me_fragment";
    private static final String SEARCH_FRAGMENT_NAME = "search_fragment";

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private MainActivityViewModel mainActivityViewModel;

    private final float DEFAULT_ZOOM = 14.0f;
    private LatLng mDefaultLocation = new LatLng(54.674886, 25.273520);
    private LocationUtil locationUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        locationUtil = new LocationUtil(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        HandleNavigationSwitching(bottomNavigationView);

        if(savedInstanceState == null) {
            createMapFragment();
        }
    }

    private void HandleNavigationSwitching(BottomNavigationView bottomNavigationView) {
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        final int itemId = item.getItemId();
                        if (itemId == R.id.action_discover || itemId == R.id.action_near_me ||
                                itemId == R.id.action_search) {

                            item.setChecked(true);

                            switch (itemId) {
                                case R.id.action_discover:
                                    // Creates new MapFragment if one doesn't exist
                                    // If map fragment exists, and is visible, nothing happens
                                    // If exists, but not visible, then finds the MapFragment in backstack
                                    // and changes to it
                                    if (!fragmentExists(MAP_FRAGMENT_NAME)) {
                                        createMapFragment();
                                    } else if (!fragmentIsVisible(MAP_FRAGMENT_NAME)) {
                                        Fragment mapFragmentFromBackstack = getSupportFragmentManager().findFragmentByTag(MAP_FRAGMENT_NAME);
                                        changeFragment(mapFragmentFromBackstack, MAP_FRAGMENT_NAME);
                                    }
                                    break;

                                    // Same logic as with MapFragment, but with NearMeFragment
                                case R.id.action_near_me:
                                    if (!fragmentExists(NEAR_ME_FRAGMENT_NAME)) {
                                        createNearMeFragmentAndChangeToIt();
                                        generateFakeData();
                                    } else if (!fragmentIsVisible(NEAR_ME_FRAGMENT_NAME)) {
                                        Fragment nearMeFragmentFromBackstack = getSupportFragmentManager().findFragmentByTag(NEAR_ME_FRAGMENT_NAME);
                                        changeFragment(nearMeFragmentFromBackstack, NEAR_ME_FRAGMENT_NAME);
                                    }
                                    break;

                                // Same logic as with MapFragment, but with SearchFragment
                                case R.id.action_search:
                                    if (!fragmentExists(SEARCH_FRAGMENT_NAME)) {
                                        createSearchFragmentAndChangeToIt();
                                    } else if (!fragmentIsVisible(SEARCH_FRAGMENT_NAME)) {
                                        Fragment searchFragmentFromBackstack = getSupportFragmentManager().findFragmentByTag(SEARCH_FRAGMENT_NAME);
                                        changeFragment(searchFragmentFromBackstack, SEARCH_FRAGMENT_NAME);
                                    }
                                    break;
                            }
                        }
                        return false;
                    }
                });

    }

    private boolean fragmentExists(String fragmentName) {
        Fragment fragmentToCheck = getSupportFragmentManager().findFragmentByTag(fragmentName);
        return fragmentToCheck != null;
    }

    private boolean fragmentIsVisible(String fragmentName) {
        Fragment fragmentToCheck = getSupportFragmentManager().findFragmentByTag(fragmentName);
        return fragmentToCheck.isVisible();
    }

    private void createMapFragment() {
        if (findViewById(R.id.fragment_in_main_activity) != null) {

            // Create a new Fragment to be placed in the activity layout
            mapFragment = new SupportMapFragment();
            mapFragment.getMapAsync(this);

            // Add the fragment to the 'fragment_in_main_activity' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_in_main_activity, mapFragment, MAP_FRAGMENT_NAME).commit();
        }
    }

    private void createNearMeFragmentAndChangeToIt() {
        Fragment shopCardListFragment = new ShopCardListFragment();
        changeFragment(shopCardListFragment, NEAR_ME_FRAGMENT_NAME);
    }

    private void createSearchFragmentAndChangeToIt() {
        Fragment searchViewFragment = new SearchViewFragment();
        changeFragment(searchViewFragment, SEARCH_FRAGMENT_NAME);
    }

    private void changeFragment(Fragment fragmentToChange, String fragmentName) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_in_main_activity, fragmentToChange);
        transaction.addToBackStack(fragmentName);

        // Commit the transaction
        transaction.commit();
    }

    public void onDiscountedItemsFragmentInteraction(DiscountedItem item){
        Toast.makeText(this, item.getName() + ", hadouken.", Toast.LENGTH_SHORT).show();
    }

    private void generateFakeData() {
        List<ShopCard> fakeShops = new ArrayList<>();

        ShopCard ikiShop = new ShopCard("Iki", "Baltupiu g. 69", R.drawable.iki_logo, 3);
        ShopCard maximaShop = new ShopCard("Maxima", "Kalvariju g. 6", R.drawable.iki_logo, 15);

        fakeShops.add(ikiShop);
        fakeShops.add(maximaShop);

        mainActivityViewModel.setShopCardList(fakeShops);
    }

    public void onShopListFragmentInteraction(ShopCard item){

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

        // Add a marker and zoom into current location
        locationUtil.getDeviceLocation(new LocationCallback() {
         @Override
         public void onComplete(Location location) {
             LatLng currLoc = new LatLng(location.getLatitude(), location.getLongitude());
             mMap.addMarker(new MarkerOptions().position(currLoc).title("Current Location"));

             mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                     new LatLng(location.getLatitude(),
                             location.getLongitude()), DEFAULT_ZOOM));

             updateLocationUI();
         }
        });
    }

    /**
    Example how to get current location:

     locationUtil.getDeviceLocation(new LocationUtil.LocationCallback() {
        @Override
        public void onComplete(Location location) {
            // Write code that uses the location here
        }
     });

     */


    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (locationUtil.mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                locationUtil.mLastKnownLocation = null;
                locationUtil.getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
