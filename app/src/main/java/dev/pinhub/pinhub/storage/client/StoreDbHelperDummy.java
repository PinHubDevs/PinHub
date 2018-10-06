package dev.pinhub.pinhub.storage.client;

import java.util.ArrayList;
import java.util.List;

import dev.pinhub.pinhub.storage.client.models.StoreItem;

public class StoreDbHelperDummy implements StoreDbHelper {
    @Override
    public void getStoresByType(String query, StoreDbCallback storeDbCallback) {
        StoreItem storeItem = new StoreItem();
        storeItem.setId(0);

        List<StoreItem> tempList = new ArrayList<>();
        tempList.add(storeItem);

        storeDbCallback.onCompleteList(tempList);
    }

    @Override
    public void getStoresNearMe(StoreDbCallback storeDbCallback) {

    }

    @Override
    public void getStoresHere(StoreDbCallback storeDbCallback) {

    }
}
