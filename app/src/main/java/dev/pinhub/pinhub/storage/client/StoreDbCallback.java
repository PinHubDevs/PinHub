package dev.pinhub.pinhub.storage.client;

import java.util.Collection;

import dev.pinhub.pinhub.storage.client.models.StoreItem;

public interface StoreDbCallback {
    void onCompleteList(Collection<StoreItem> storeItem);
}
