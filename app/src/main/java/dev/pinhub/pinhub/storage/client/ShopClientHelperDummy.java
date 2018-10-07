package dev.pinhub.pinhub.storage.client;

import java.util.ArrayList;
import java.util.List;

import dev.pinhub.pinhub.R;
import dev.pinhub.pinhub.storage.client.models.StoreItem;

public class ShopClientHelperDummy implements ShopClientHelper {
    @Override
    public void getStoresByType(String query, ShopClientCallback shopClientCallback) {
        StoreItem storeItem = new StoreItem();
        storeItem.setId(0);

        List<StoreItem> tempList = new ArrayList<>();
        tempList.add(storeItem);

        shopClientCallback.onCompleteList(tempList);
    }

    @Override
    public void getStoresNearMe(ShopClientCallback shopClientCallback) {
        List<StoreItem> fakeShops = new ArrayList<>();

        StoreItem ikiShop = new StoreItem("Iki", "Baltupiu g. 69", R.drawable.iki_logo, 3);
        StoreItem maximaShop = new StoreItem("Maxima", "Kalvariju g. 6", R.drawable.iki_logo, 15);

        fakeShops.add(ikiShop);
        fakeShops.add(maximaShop);

        shopClientCallback.onCompleteList(fakeShops);
    }

    @Override
    public void getStoresHere(ShopClientCallback shopClientCallback) {
        List<StoreItem> fakeShops = new ArrayList<>();

        StoreItem ikiShop = new StoreItem("Iki", "Baltupiu g. 69", R.drawable.iki_logo, 3);
        StoreItem maximaShop = new StoreItem("Maxima", "Kalvariju g. 6", R.drawable.iki_logo, 15);

        fakeShops.add(ikiShop);
        fakeShops.add(maximaShop);

        shopClientCallback.onCompleteList(fakeShops);
    }
}
