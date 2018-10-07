package dev.pinhub.pinhub.storage.client;

import java.util.List;

import dev.pinhub.pinhub.storage.client.models.DiscountedItem;

public interface DiscountClientCallback {
    void onCompleteList(List<DiscountedItem> discountedItems);
    void onCompleteCount(int discountedItemCount);
}
