package dev.pinhub.pinhub.location;

public interface Location {
    int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    void getLocationPermission();
    void getDeviceLocation(final LocationCallback callback);
}
