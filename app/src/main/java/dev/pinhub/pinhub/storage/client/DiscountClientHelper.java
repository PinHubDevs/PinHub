package dev.pinhub.pinhub.storage.client;

public interface DiscountClientHelper {
    void getDiscountByStoreId(int storeId, DiscountClientCallback discountClientCallback);
    void getDiscountCountByStoreId(int storeId, DiscountClientCallback discountClientCallback);
}
