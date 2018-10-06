package dev.pinhub.pinhub.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import dev.pinhub.pinhub.storage.client.models.StoreItem;

public class MainActivityViewModel extends ViewModel {

    // Observable type object that holds the shops
    private MutableLiveData<List<StoreItem>> shops;

    public MainActivityViewModel() {
        shops = new MutableLiveData<>();
    }

    public LiveData<List<StoreItem>> getShops() {
        if(shops == null) {
            shops = new MutableLiveData<>();
        }
        return shops;
    }

    // Sets the value of the observable and propagates the changes to all subscribers
    public void setShopCardList(List<StoreItem> shopList) {
        shops.setValue(shopList);
    }

}
