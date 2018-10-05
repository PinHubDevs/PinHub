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

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener shopCardInteractionListener;
    private Observer<List<ShopCard>> shopCardListObserver;
    private MainActivityViewModel mainActivityViewModel;
    private List<ShopCard> shops;

    public ShopCardListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ShopCardListFragment newInstance(int columnCount) {
        ShopCardListFragment fragment = new ShopCardListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        shops = new ArrayList<>();

        createDiscountedItemsObserver();
        mainActivityViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
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
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
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
