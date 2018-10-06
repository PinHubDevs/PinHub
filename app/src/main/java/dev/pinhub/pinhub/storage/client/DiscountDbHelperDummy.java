package dev.pinhub.pinhub.storage.client;

import java.util.ArrayList;
import java.util.List;

import dev.pinhub.pinhub.R;
import dev.pinhub.pinhub.storage.client.models.DiscountedItem;

public class DiscountDbHelperDummy implements DiscountDbHelper {
    @Override
    public void getDiscountByStoreId(int storeId, DiscountDbCallback discountItemCallback) {
        List<DiscountedItem> items = new ArrayList<>();

        for(int i = 0; i < 35; i++){
            items.add(new DiscountedItem("Duona " + i, "Jore", R.drawable.iki_logo, 3.99, 15));
        }
        discountItemCallback.onCompleteList(items);
    }

    @Override
    public void getDiscountCountByStoreId(int storeId, DiscountDbCallback discountItemCallback) {
        discountItemCallback.onCompleteCount(5);
    }
}
