package dev.pinhub.pinhub.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dev.pinhub.pinhub.R;
import dev.pinhub.pinhub.fragments.DiscountedItemFragment.OnListFragmentInteractionListener;
import dev.pinhub.pinhub.storage.client.models.DiscountedItem;

public class DiscountedItemRecyclerViewAdapter extends RecyclerView.Adapter<DiscountedItemRecyclerViewAdapter.ViewHolder> {

    private final List<DiscountedItem> discountedItems;
    private final OnListFragmentInteractionListener interactionListener;

    public DiscountedItemRecyclerViewAdapter(List<DiscountedItem> items, OnListFragmentInteractionListener listener) {
        discountedItems = items;
        interactionListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_discounteditem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.item = discountedItems.get(position);
        holder.name.setText(discountedItems.get(position).getName());
        holder.description.setText(discountedItems.get(position).getDescription());
        holder.image.setImageResource(discountedItems.get(position).getImageResourceId());
        holder.price.setText(String.valueOf(discountedItems.get(position).getDiscountedPrice()) + "€");
        holder.discount.setText(String.valueOf(discountedItems.get(position).getDiscountPercentage()) + "%");

        holder.discountedItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != interactionListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    interactionListener.onDiscountedItemsFragmentInteraction(holder.item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return discountedItems.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View discountedItemView;
        public final TextView name;
        public final TextView description;
        public final ImageView image;
        public final TextView price;
        public final TextView discount;
        public DiscountedItem item;

        public ViewHolder(View view) {
            super(view);
            discountedItemView = view;
            name = (TextView) view.findViewById(R.id.discount_name);
            description = (TextView) view.findViewById(R.id.discount_description);
            image = (ImageView) view.findViewById(R.id.discount_image);
            price = (TextView) view.findViewById(R.id.discounted_price);
            discount = (TextView) view.findViewById(R.id.discount_percentage);
        }

    }
}
