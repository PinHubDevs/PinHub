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
import dev.pinhub.pinhub.adapters.DiscountedItemRecyclerViewAdapter;
import dev.pinhub.pinhub.storage.client.models.DiscountedItem;
import dev.pinhub.pinhub.models.DiscountedItemViewModel;

public class DiscountedItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int columnCount = 1;
    private OnListFragmentInteractionListener interactionsListener;
    private DiscountedItemViewModel viewModel;
    private Observer<List<DiscountedItem>> discountedItemObserver;
    private List<DiscountedItem> discountedItems;

    public DiscountedItemFragment() {
    }

    @SuppressWarnings("unused")
    public static DiscountedItemFragment newInstance(int columnCount) {
        DiscountedItemFragment fragment = new DiscountedItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        discountedItems = new ArrayList<>();

        createDiscountedItemsObserver();
        viewModel = ViewModelProviders.of(getActivity()).get(DiscountedItemViewModel.class);
        viewModel.getDiscountedItems().observe(getActivity(), discountedItemObserver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discounteditem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (columnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
            }
            recyclerView.setAdapter(new DiscountedItemRecyclerViewAdapter(discountedItems, interactionsListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            interactionsListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionsListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnListFragmentInteractionListener {
        void onDiscountedItemsFragmentInteraction(DiscountedItem item);
    }

    // TODO: Probably should move to ViewAdapter
    private void createDiscountedItemsObserver() {
        discountedItemObserver = new Observer<List<DiscountedItem>>() {
            @Override
            public void onChanged(@Nullable final List<DiscountedItem> items) {
                discountedItems.addAll(items);
            }
        };
    }
}
