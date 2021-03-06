package dev.pinhub.pinhub.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Collection;
import java.util.List;

import dev.pinhub.pinhub.storage.client.models.DiscountedItem;

public class DiscountedItemViewModel extends ViewModel {
    private MutableLiveData<List<DiscountedItem>> discountedItems;

    public LiveData<List<DiscountedItem>> getDiscountedItems() {
        if(discountedItems == null) {
            discountedItems = new MutableLiveData<>();
        }
        return discountedItems;
    }

    public void setDiscountList(List<DiscountedItem> listOfDiscounts) {
        discountedItems.setValue(listOfDiscounts);
    }
}
