package dev.pinhub.pinhub.storage.client;

import java.util.Collection;
import java.util.List;

import dev.pinhub.pinhub.storage.client.models.StoreItem;

public interface ShopClientCallback {
    void onCompleteList(List<StoreItem> storeItems);
}
