package dev.pinhub.pinhub.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dev.pinhub.pinhub.R;
import dev.pinhub.pinhub.fragments.ShopCardListFragment.OnListFragmentInteractionListener;
import dev.pinhub.pinhub.storage.client.models.ShopItem;


public class ShopCardListRecyclerViewAdapter extends RecyclerView.Adapter<ShopCardListRecyclerViewAdapter.ViewHolder> {

    private final List<ShopItem> shopItems;
    private final OnListFragmentInteractionListener listFragmentInteractionListener;

    public ShopCardListRecyclerViewAdapter(List<ShopItem> items, OnListFragmentInteractionListener listener) {
        shopItems = items;
        listFragmentInteractionListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_shopcardlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = shopItems.get(position);
        holder.mIdView.setText(shopItems.get(position).getName());
        holder.mContentView.setText(shopItems.get(position).getAddress());

        holder.shopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listFragmentInteractionListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listFragmentInteractionListener.onShopListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View shopView;
        public final TextView mIdView;
        public final TextView mContentView;
        public ShopItem mItem;

        public ViewHolder(View view) {
            super(view);
            shopView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}
