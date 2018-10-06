package dev.pinhub.pinhub.storage.client;

public interface DiscountDbHelper {
    void getDiscountByStoreId(int storeId, DiscountDbCallback discountDbCallback);
    void getDiscountCountByStoreId(int storeId, DiscountDbCallback discountDbCallback);
}
