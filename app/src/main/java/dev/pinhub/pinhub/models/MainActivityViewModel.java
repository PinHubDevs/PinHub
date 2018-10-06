package dev.pinhub.pinhub.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import dev.pinhub.pinhub.storage.client.models.ShopCard;

public class MainActivityViewModel extends ViewModel {

    // Observable type object that holds the shops
    private MutableLiveData<List<ShopCard>> shops;

    public MainActivityViewModel() {
        shops = new MutableLiveData<>();
    }

    public LiveData<List<ShopCard>> getShops() {
        if(shops == null) {
            shops = new MutableLiveData<>();
        }
        return shops;
    }

    // Sets the value of the observable and propagates the changes to all subscribers
    public void setShopCardList(List<ShopCard> shopList) {
        shops.setValue(shopList);
    }

}
