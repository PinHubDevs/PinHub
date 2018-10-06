package dev.pinhub.pinhub.storage.client;

public interface StoreDbHelper {
    void getStoresByType(String query, StoreDbCallback storeDbCallback);
    void getStoresNearMe(StoreDbCallback storeDbCallback);
    void getStoresHere(StoreDbCallback storeDbCallback);
}
