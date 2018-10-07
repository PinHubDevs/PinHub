package dev.pinhub.pinhub.storage.client;

public interface ShopClientHelper {
    void getStoresByType(String query, ShopClientCallback shopClientCallback);
    void getStoresNearMe(ShopClientCallback shopClientCallback);
    void getStoresHere(ShopClientCallback shopClientCallback);
}
