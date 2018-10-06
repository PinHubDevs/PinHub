package dev.pinhub.pinhub.storage.client;

import java.util.List;

import dev.pinhub.pinhub.storage.client.models.DiscountedItem;

public interface DiscountDbCallback {
    void onCompleteList(List<DiscountedItem> discountedItems);
    void onCompleteCount(int discountedItemCount);
}
