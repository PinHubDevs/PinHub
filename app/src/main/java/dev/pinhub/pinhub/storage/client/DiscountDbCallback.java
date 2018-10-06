package dev.pinhub.pinhub.storage.client;

import java.util.Collection;

import dev.pinhub.pinhub.storage.client.models.DiscountedItem;

public interface DiscountDbCallback {
    void onCompleteList(Collection<DiscountedItem> discountedItem);
    void onCompleteCount(int discountedItemCount);
}
