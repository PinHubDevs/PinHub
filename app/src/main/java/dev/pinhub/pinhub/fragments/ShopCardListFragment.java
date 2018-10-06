package dev.pinhub.pinhub.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dev.pinhub.pinhub.R;
import dev.pinhub.pinhub.adapters.ShopCardListRecyclerViewAdapter;
import dev.pinhub.pinhub.models.MainActivityViewModel;
import dev.pinhub.pinhub.models.ShopCard;

public class ShopCardListFragment extends Fragment {

    private static final int COLUMN_COUNT = 1;

    private OnListFragmentInteractionListener shopCardInteractionListener;
    private MainActivityViewModel mainActivityViewModel;
    private Observer<List<ShopCard>> shopCardListObserver;
    private List<ShopCard> shops;

    public ShopCardListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shops = new ArrayList<>();

        createDiscountedItemsObserver();

        // Get the MainActivityViewModel from MainActivity
        mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        // Start observing (subscribing) shops list observable data type
        mainActivityViewModel.getShops().observe(getActivity(), shopCardListObserver);
    }

    // Create the observer object and subscribe to MainActivityViewModel's shops observable
    private void createDiscountedItemsObserver() {
        shopCardListObserver = new Observer<List<ShopCard>>() {

            // When a change in data is received, add all new ShopCards to the RecyclerView
            @Override
            public void onChanged(@Nullable final List<ShopCard> items) {
                shops.addAll(items);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopcardlist_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (COLUMN_COUNT <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, COLUMN_COUNT));
            }
            recyclerView.setAdapter(new ShopCardListRecyclerViewAdapter(shops, shopCardInteractionListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            shopCardInteractionListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        shopCardInteractionListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onShopListFragmentInteraction(ShopCard item);
    }
}
