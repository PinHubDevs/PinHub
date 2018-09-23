package dev.pinhub.pinhub.LocationUtilities;

public interface LocationBase {
    int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    void getLocationPermission();
    void getDeviceLocation(final LocationCallback callback);
}
