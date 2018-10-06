package dev.pinhub.pinhub.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.SearchView;

import java.util.List;

import dev.pinhub.pinhub.DiscountedProductListActivity;
import dev.pinhub.pinhub.R;
import dev.pinhub.pinhub.storage.client.ShopClientCallback;
import dev.pinhub.pinhub.storage.client.ShopClientHelper;
import dev.pinhub.pinhub.storage.client.ShopClientHelperDummy;
import dev.pinhub.pinhub.storage.client.models.StoreItem;

public class SearchViewFragment extends Fragment {
    private SearchView search;
    private ShopClientHelper shopClientHelper;

    public SearchViewFragment()
    {
        this.shopClientHelper = new ShopClientHelperDummy();
    }

    public static SearchViewFragment newInstance() {
        return new SearchViewFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_searchview, container, false);

        GridLayout gridLayout = rootView.findViewById(R.id.grid_layout);
        search = rootView.findViewById(R.id.search_bar);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!s.contains("Bread")){
                    return false;
                }

                final Intent discountedProductListActivity = new Intent(getActivity(), DiscountedProductListActivity.class);
                shopClientHelper.getStoresByType(s, new ShopClientCallback() {
                    @Override
                    public void onCompleteList(List<StoreItem> storeItem) {
                        discountedProductListActivity.putExtra("storeId", storeItem.get(0).getId());
                        startActivity(discountedProductListActivity);
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        for(int x = 0; x < 10; x++) {
            Button bt = new Button(getActivity());
            final String text = "Bread" + x;
            bt.setText(text);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent discountedProductListActivity = new Intent(getActivity(), DiscountedProductListActivity.class);

                    shopClientHelper.getStoresByType(text, new ShopClientCallback() {
                        @Override
                        public void onCompleteList(List<StoreItem> storeItem) {
                            discountedProductListActivity.putExtra("storeId", storeItem.get(0).getId());
                            startActivity(discountedProductListActivity);
                        }
                    });
                }
            });
            gridLayout.addView(bt);
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
