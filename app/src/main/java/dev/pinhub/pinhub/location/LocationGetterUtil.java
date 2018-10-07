package dev.pinhub.pinhub.location;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static java.lang.Thread.sleep;


public class LocationGetterUtil implements LocationGetter {
    public Boolean mLocationPermissionGranted = false;
    private FusedLocationProviderClient mFusedLocationClient;
    public android.location.Location mLastKnownLocation;
    private Activity activity;

    public LocationGetterUtil(Activity activity) {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity.getApplicationContext());
        this.activity = activity;
        getLocationPermission();
    }


    public void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(activity.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(activity,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }


    public void getDeviceLocation(final LocationCallback callback) {
        try {
            while (!mLocationPermissionGranted) {
                try {
                    getLocationPermission();
                    sleep(200);
                } catch (InterruptedException e) {
                    Log.e("Exception: %s", e.getMessage());
                }
            }
            Task locationResult = mFusedLocationClient.getLastLocation();
            locationResult.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        callback.onComplete((android.location.Location) task.getResult());
                        mLastKnownLocation = (android.location.Location) task.getResult();
                    }
                }
            });
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
}
