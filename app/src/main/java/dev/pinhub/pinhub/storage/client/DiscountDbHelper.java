package dev.pinhub.pinhub.storage.client;

public interface DiscountDbHelper {
    void getDiscountByStoreId(int storeId, DiscountDbCallback discountItemCallback);
    void getDiscountCountByStoreId(int storeId, DiscountDbCallback discountItemCallback);
}
